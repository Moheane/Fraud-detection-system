
package com.frauddetectionapp.services.flaggedtransaction.fraudrules;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RapidTransactionRule implements FraudRuleInterface {

    private final TransactionRepository transactionRepository;

    private static final int LIMIT         = 3;
    private static final int WINDOW_SECONDS = 60;

    @Override
    public Optional<FraudRuleHitResults> evaluate(Transaction transaction) {
        String windowStart = LocalDateTime.now().minusSeconds(WINDOW_SECONDS).toString();

        long recentCount = transactionRepository
                .countByUserIdAndTimeStampAfter(transaction.getUserId(), windowStart);

        if (recentCount >= LIMIT) {

            return Optional.of(new FraudRuleHitResults(
                    FraudRule.RAPID_TRANSACTIONS,
                    "User made " + recentCount + " transactions within " + WINDOW_SECONDS + " seconds"
            ));
        }
        return Optional.empty();
    }
}
