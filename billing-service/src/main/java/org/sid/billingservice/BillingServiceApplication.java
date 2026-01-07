package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.kafka.BillEvent;
import org.sid.billingservice.kafka.KafkaProducerService;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.security.SecureRandom;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BillingServiceApplication {



    private static final SecureRandom RANDOM = new SecureRandom();


    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient,
                                        KafkaProducerService kafkaProducerService) {
        return args -> {
            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();

            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build();
                Bill savedBill = billRepository.save(bill);

                // Publier l'événement Kafka pour la création de facture
                BillEvent billEvent = BillEvent.builder()
                        .billId(savedBill.getId())
                        .customerId(savedBill.getCustomerId())
                        .billingDate(savedBill.getBillingDate())
                        .eventType("CREATED")
                        .eventTimestamp(new Date())
                        .build();
                kafkaProducerService.sendBillEvent(billEvent);

                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1 + RANDOM.nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });
        };
    }
}
