package com.frauddetectionapp.repositories.transaction;

import org.springframework.stereotype.Component;

@Component
public class TransactionQueryScripts {

    public String findAllTransactions() {
        return "SELECT t FROM Transaction t";
    }

    public String findByChannel() {
        return "SELECT t FROM Transaction t WHERE t.channel = :channel";
    }

    public String findByUserId() {
        return "SELECT t FROM Transaction t WHERE t.userId = :userId";
    }

    public String findByPaymentCategory() {
        return "SELECT t FROM Transaction t WHERE t.paymentCategory = :paymentCategory";
    }
}