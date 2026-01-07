package org.sid.billingservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "bill-events", groupId = "billing-service-group")
    public void consumeBillEvent(
            @Payload BillEvent billEvent,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("========== Événement Bill reçu ==========");
        logger.info("Topic: {}", topic);
        logger.info("Partition: {}", partition);
        logger.info("Offset: {}", offset);
        logger.info("Bill ID: {}", billEvent.getBillId());
        logger.info("Customer ID: {}", billEvent.getCustomerId());
        logger.info("Event Type: {}", billEvent.getEventType());
        logger.info("Billing Date: {}", billEvent.getBillingDate());
        logger.info("==========================================");
        
        // Traiter l'événement ici
        processBillEvent(billEvent);
    }

    @KafkaListener(topics = "customer-events", groupId = "billing-service-group")
    public void consumeCustomerEvent(@Payload Object event) {
        logger.info("Événement Customer reçu: {}", event);
        // Traiter l'événement customer ici
    }

    private void processBillEvent(BillEvent billEvent) {
        // Logique de traitement de l'événement
        switch (billEvent.getEventType()) {
            case "CREATED":
                logger.info("Traitement de la création de facture: {}", billEvent.getBillId());
                break;
            case "UPDATED":
                logger.info("Traitement de la mise à jour de facture: {}", billEvent.getBillId());
                break;
            case "DELETED":
                logger.info("Traitement de la suppression de facture: {}", billEvent.getBillId());
                break;
            default:
                logger.warn("Type d'événement inconnu: {}", billEvent.getEventType());
        }
    }
}

