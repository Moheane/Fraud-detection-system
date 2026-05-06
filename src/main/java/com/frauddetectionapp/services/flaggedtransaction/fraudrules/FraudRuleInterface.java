package com.frauddetectionapp.services.flaggedtransaction.fraudrules;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Transaction;

import java.util.Optional;

public interface FraudRuleInterface {
    Optional<FraudRuleHitResults> evaluate(Transaction transaction);
}
