package com.frauddetectionapp.Entities.flaggedtransaction;

import com.frauddetectionapp.Entities.transaction.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FlaggedTransactionTable")
@Builder
public class FlaggedTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;
    private String amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String timeStamp;
    @Enumerated(EnumType.STRING)
    private Location location;
    @Enumerated(EnumType.STRING)
    private Method method;
    @Enumerated(EnumType.STRING)
    private Channel channel;
    private String reference;
    @Enumerated(EnumType.STRING)
    private PaymentCategory paymentCategory;
    private String triggeredRules;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Status transactionStatus;

}