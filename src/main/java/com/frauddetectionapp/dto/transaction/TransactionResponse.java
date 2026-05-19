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
    private String amount;
    private Currency currency;
    private String timeStamp;
    private Location location;
    private Method method;
    private Channel channel;
    private String reference;
    private PaymentCategory paymentCategory;

    public TransactionResponse(long l, String john, String number, Object o, Object o1, Object o2, Object o3, Object o4, String ref123, Object o5) {
    }
}
