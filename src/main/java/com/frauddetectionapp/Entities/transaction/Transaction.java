package com.frauddetectionapp.Entities.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TransactionsTable")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String userId;
    private String UserName;
    private String amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String timeStamp;
    @Enumerated(EnumType.STRING)
    private Location location;
    private String deviceId;
    @Enumerated(EnumType.STRING)
    private Method method;
    @Enumerated(EnumType.STRING)
    private Channel channel;
    private String reference;
    @Enumerated(EnumType.STRING)
    private PaymentCategory paymentCategory;
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    private void generateDeviceId() {
        if (deviceId == null || deviceId.isBlank()) {
            int number = (int)(Math.random() * 90000) + 10000;
            deviceId = String.valueOf(number);
        }
    }
}