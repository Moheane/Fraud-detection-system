package com.frauddetectionapp.repositories.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.transaction.*;
import com.frauddetectionapp.repositories.transaction.TransactionRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

@Repository
class FlaggedTransactionRepositoryImpl implements FlaggedTransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private QueryScripts queryScripts;

    @Override
    public List<FlaggedTransaction> findFlaggedTransactionLocation(String location) {

        Location locationCategory = Location.valueOf(location.toUpperCase());

        return em.createQuery(
                        queryScripts.findByLocation(), FlaggedTransaction.class)
                .setParameter("locationCategory", locationCategory)
                .getResultList()
                .stream().toList();
    }

    @Override
    public List<FlaggedTransaction> findFlaggedTransactionChannel(String channel) {
        Channel channelCategory = Channel.valueOf(channel.toUpperCase()); // convert String → enum

        return em.createQuery(
                        queryScripts.findByChannel(), FlaggedTransaction.class)
                .setParameter("channelCategory", channelCategory)
                .getResultList()
                .stream().toList();
    }

    @Override
    public List<FlaggedTransaction> findFlaggedTransactionStatus(String status) {
        Status statusCategory = Status.valueOf(status.toUpperCase()); // convert String → enum

        return em.createQuery(
                        queryScripts.findByStatus(), FlaggedTransaction.class)
                .setParameter("statusCategory", statusCategory)
                .getResultList()
                .stream().toList();
    }

    @Override
    public List<FlaggedTransaction> findFlaggedTransactionPaymentCategory(String category) {
        PaymentCategory paymentCategory = PaymentCategory.valueOf(category.toUpperCase()); // convert String → enum

        return em.createQuery(
                        queryScripts.findByPaymentCategory(), FlaggedTransaction.class)
                .setParameter("paymentCategory", paymentCategory)
                .getResultList()
                .stream().toList();
    }
}
