package com.frauddetectionapp.repositories.transaction;

import com.frauddetectionapp.Entities.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {

    boolean existsByReferenceAndTransactionIdNot(String reference, Long transactionId);

    long countByUserIdAndTimeStampAfter(String userId, String timeStamp);

    long countByUserIdAndStatusAndTimeStampAfter(String userId, String status, String timeStamp);
}