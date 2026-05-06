package com.frauddetectionapp.dto.transaction;

import com.frauddetectionapp.Entities.transaction.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionsMapper {

    public TransactionResponse toResponseDTO(Transaction transaction) {

        TransactionResponse results = new TransactionResponse();

        results.setTransactionId(transaction.getTransactionId());
        results.setAmount(transaction.getAmount());
        results.setCurrency(transaction.getCurrency());
        results.setChannel(transaction.getChannel());
        results.setMethod(transaction.getMethod());
        results.setLocation(transaction.getLocation());
        results.setUserName(transaction.getUserName());
        results.setReference(transaction.getReference());
        results.setTimeStamp(transaction.getTimeStamp());
        results.setPaymentCategory(transaction.getPaymentCategory());



        return results;


    }

    public Transaction toEntity(TransactionRequest transactionRequest) {


        Transaction results = new Transaction();

        results.setStatus(randomStatus());
        results.setAmount(transactionRequest.getAmount());
        results.setCurrency(randomCurrency());
        results.setUserName(transactionRequest.getUserName());
        results.setReference(transactionRequest.getReference());
        results.setChannel(randomChannel());
        results.setMethod(randomizeMethod());
        results.setLocation(randomLocation());
        results.setTimeStamp(String.valueOf(LocalDateTime.now()));
        results.setPaymentCategory(randomPaymentCategory());

        return results;

    }

    private PaymentCategory randomPaymentCategory() {
        PaymentCategory[] categories = PaymentCategory.values();
        return categories[(int)(Math.random() * categories.length)];
    }

    private Channel randomChannel() {
        Channel[] channels = Channel.values();
        return channels[(int)(Math.random() * channels.length)];
    }

    private Status randomStatus() {
        Status[] statuses = Status.values();
        return statuses[(int)(Math.random() * statuses.length)];
    }

    private Method randomizeMethod() {
        Method[] Method = com.frauddetectionapp.Entities.transaction.Method.values();
        return Method[(int)(Math.random() * Method.length)];
    }

    private Location randomLocation() {
        Location[] Location = com.frauddetectionapp.Entities.transaction.Location.values();
        return Location[(int)(Math.random() * Location.length)];
    }

    private Currency randomCurrency() {
        Currency[] Currency = com.frauddetectionapp.Entities.transaction.Currency.values();
        return Currency[(int)(Math.random() * Currency.length)];
    }


}
