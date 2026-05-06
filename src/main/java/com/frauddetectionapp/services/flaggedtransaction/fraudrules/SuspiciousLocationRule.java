package com.frauddetectionapp.services.flaggedtransaction.fraudrules;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class SuspiciousLocationRule implements FraudRuleInterface {

    private static final Set<String> SUSPICIOUS_LOCATIONS = Set.of(
            "NORTH_KOREA", "IRAN", "SYRIA", "CUBA"
    );

    @Override
    public Optional<FraudRuleHitResults> evaluate(Transaction transaction) {
        String location = String.valueOf(transaction.getLocation());

        if (location != null && SUSPICIOUS_LOCATIONS.contains(location)) {
            return Optional.of(new FraudRuleHitResults(
                    "SUSPICIOUS_LOCATIONS",
                    "Transaction originated from flagged location: " + location
            ));
        }
        return Optional.empty();
    }
}