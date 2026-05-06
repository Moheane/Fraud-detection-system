package com.frauddetectionapp.repositories.transaction;

import com.frauddetectionapp.Entities.transaction.Transaction;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<Transaction> findAllTransactions();
    List<Transaction> findByChannel(String channel);
    List<Transaction> findTransactionsByUserId(String userId);
    List<Transaction> findTransactionsByUserPaymentCategory(String paymentCategory);

}