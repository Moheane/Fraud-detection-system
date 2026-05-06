package com.frauddetectionapp.dto.transaction;

import com.frauddetectionapp.Entities.transaction.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long transactionId;
    private String UserName;
    private String amount;
    private Currency currency;
    private String timeStamp;
    private Location location;
    private Method method;
    private Channel channel;
    private String reference;
    private PaymentCategory paymentCategory;
}
