package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.kafka.BillEvent;
import org.sid.billingservice.kafka.KafkaProducerService;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class BillRestController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;
    private final KafkaProducerService kafkaProducerService;

    public BillRestController(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient,
            KafkaProducerService kafkaProducerService
    ) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @PostMapping("/bills")
    public Bill createBill(@RequestBody Bill bill) {
        bill.setBillingDate(new Date());
        Bill savedBill = billRepository.save(bill);
        
        // Publier l'événement Kafka
        BillEvent billEvent = BillEvent.builder()
                .billId(savedBill.getId())
                .customerId(savedBill.getCustomerId())
                .billingDate(savedBill.getBillingDate())
                .eventType("CREATED")
                .eventTimestamp(new Date())
                .build();
        
        kafkaProducerService.sendBillEvent(billEvent);
        
        return savedBill;
    }

    @PutMapping("/bills/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Bill existingBill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        
        existingBill.setBillingDate(bill.getBillingDate());
        existingBill.setCustomerId(bill.getCustomerId());
        Bill updatedBill = billRepository.save(existingBill);
        
        // Publier l'événement Kafka
        BillEvent billEvent = BillEvent.builder()
                .billId(updatedBill.getId())
                .customerId(updatedBill.getCustomerId())
                .billingDate(updatedBill.getBillingDate())
                .eventType("UPDATED")
                .eventTimestamp(new Date())
                .build();
        
        kafkaProducerService.sendBillEvent(billEvent);
        
        return updatedBill;
    }

    @DeleteMapping("/bills/{id}")
    public void deleteBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        
        billRepository.deleteById(id);
        
        // Publier l'événement Kafka
        BillEvent billEvent = BillEvent.builder()
                .billId(bill.getId())
                .customerId(bill.getCustomerId())
                .billingDate(bill.getBillingDate())
                .eventType("DELETED")
                .eventTimestamp(new Date())
                .build();
        
        kafkaProducerService.sendBillEvent(billEvent);
    }
}
