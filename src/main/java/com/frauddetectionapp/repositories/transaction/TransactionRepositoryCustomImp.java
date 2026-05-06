package com.frauddetectionapp.repositories.transaction;

import com.frauddetectionapp.Entities.transaction.Channel;
import com.frauddetectionapp.Entities.transaction.PaymentCategory;
import com.frauddetectionapp.Entities.transaction.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionQueryScripts transactionQueryScripts;

    @Override
    public List<Transaction> findAllTransactions() {
        return em.createQuery( transactionQueryScripts.findAllTransactions(), Transaction.class)
                .getResultList();
    }

    @Override
    public List<Transaction> findByChannel(String channel) {
        Channel channelCategory = Channel.valueOf(channel.toUpperCase()); // convert String → enum

        return em.createQuery(
                        transactionQueryScripts.findByChannel(), Transaction.class)
                .setParameter("channel", channelCategory)
                .getResultList()
                .stream().toList();
    }

    @Override
    public List<Transaction> findTransactionsByUserId(String userId) {
        return em.createQuery(
                        transactionQueryScripts.findByUserId(), Transaction.class)
                .setParameter("userId", userId)
                .getResultList()
                .stream().toList();
    }

    @Override
    public List<Transaction> findTransactionsByUserPaymentCategory(String paymentCategory) {
        PaymentCategory category = PaymentCategory.valueOf(paymentCategory.toUpperCase()); // convert String → enum

        return em.createQuery(
                        transactionQueryScripts.findByPaymentCategory(), Transaction.class)
                .setParameter("paymentCategory", category)
                .getResultList()
                .stream().toList();
    }
}