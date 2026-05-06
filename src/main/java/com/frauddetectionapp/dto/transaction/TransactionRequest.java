package com.frauddetectionapp.dto.transaction;

import com.frauddetectionapp.Entities.transaction.Currency;
import com.frauddetectionapp.Entities.transaction.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

        private String userName;
        private String amount;
        private Currency currency;
        private String reference;
}
