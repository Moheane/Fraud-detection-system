package com.frauddetectionapp.services.flaggedtransaction.fraudrules;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class HighAmountRule implements FraudRuleInterface {

    private static final double THRESHOLD = 10000;

    @Override
    public Optional<FraudRuleHitResults> evaluate(Transaction transaction) {
        double amount = parseAmount(transaction);

        if (amount > THRESHOLD) {
            return Optional.of(new FraudRuleHitResults(
                    "HIGH_AMOUNT",
                    "Amount " + amount + " exceeds threshold of " + THRESHOLD
            ));
        }
        return Optional.empty();
    }

    private double parseAmount(Transaction transaction) {
        try {
            return Double.parseDouble(transaction.getAmount());
        } catch (NumberFormatException | NullPointerException e) {
            log.error("Could not parse amount for transactionId={}", transaction.getTransactionId());
            return 0.0;
        }

}


}
