package com.frauddetectionapp.repositories.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlaggedTransactionRepositoryCustom {
    List<FlaggedTransaction> findFlaggedTransactionLocation(String flagStatus);
    List<FlaggedTransaction> findFlaggedTransactionFraudRule(String flagStatus);
    List<FlaggedTransaction> findFlaggedTransactionChannel(String channel);
    List<FlaggedTransaction> findFlaggedTransactionStatus(String channel);
    List<FlaggedTransaction> findFlaggedTransactionPaymentCategory(String channel);
}
