package com.frauddetectionapp.services.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.repositories.flaggedtransaction.FlaggedTransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class FlaggedTransactionService {

    private final FlaggedTransactionRepository flaggedTransactionRepository;


    public List<FlaggedTransaction> getAllFlaggedTransactions(){

        assert flaggedTransactionRepository != null;
        log.info("Transactions retrieved");

        return flaggedTransactionRepository.findAll();
    }

    public Optional<FlaggedTransaction> getByTransactionId(Long transactionId) {
        log.info("Getting transaction by id {}", transactionId);

        return flaggedTransactionRepository.findById(transactionId);
    }

    public List<FlaggedTransaction> getByLocation(String location) {
        log.info("Getting transaction by location {}", location);
        return flaggedTransactionRepository.findFlaggedTransactionLocation(location);
    }

    public List<FlaggedTransaction> getByChannel(String channel) {
        log.info("Getting transaction by channel {}", channel);
        return flaggedTransactionRepository.findFlaggedTransactionChannel(channel);
    }

    public List<FlaggedTransaction> getFlaggedTransactionByStatus(String channel) {
        log.info("Getting transaction by id status");
        return flaggedTransactionRepository.findFlaggedTransactionStatus(channel);
    }

    public List<FlaggedTransaction> getByPaymentCategory(String paymentCategory) {
        log.info("Getting transaction by payment category {}", paymentCategory);
        return flaggedTransactionRepository.findFlaggedTransactionPaymentCategory(paymentCategory);
    }

    public List<FlaggedTransaction> getByFraudRule(String fraudRule) {
        log.info("Getting transaction by fraud rule {}", fraudRule);

        return flaggedTransactionRepository.findFlaggedTransactionFraudRule(fraudRule);
    }



    public void deleteTransaction(Long userId) {
        log.info("deleting transaction by id {}", userId);
        flaggedTransactionRepository.deleteById(userId);
    }


    @Transactional
    public void saveFlag(FlaggedTransaction flags) {
        flaggedTransactionRepository.save(flags);
        log.info("Saved flagged");
    }


}
