package org.sid.billingservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillEvent implements Serializable {
    private Long billId;
    private Long customerId;
    private Date billingDate;
    private String eventType; // CREATED, UPDATED, DELETED
    private Date eventTimestamp;
}

