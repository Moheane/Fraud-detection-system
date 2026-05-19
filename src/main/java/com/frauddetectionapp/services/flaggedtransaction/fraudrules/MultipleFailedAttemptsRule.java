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
public class MultipleFailedAttemptsRule implements FraudRuleInterface {

    private final TransactionRepository transactionRepository;

    private static final int    FAILED_ATTEMPTS_THRESHOLD = 3;
    private static final int    WINDOW_MINUTES            = 10;

    @Override
    public Optional<FraudRuleHitResults> evaluate(Transaction transaction) {
        String currentStatus = String.valueOf(transaction.getStatus());
        boolean isSuccessfulTransaction = currentStatus != null &&
                (currentStatus.equalsIgnoreCase("COMPLETED") ||
                        currentStatus.equalsIgnoreCase("SUCCESS"));

        if (!isSuccessfulTransaction) {
            return Optional.empty();
        }

        String windowStart = LocalDateTime.now().minusMinutes(WINDOW_MINUTES).toString();

        long failedCount = transactionRepository
                .countByUserIdAndStatusAndTimeStampAfter(
                        transaction.getUserId(),
                        "FAILED",
                        windowStart
                );

        if (failedCount >= FAILED_ATTEMPTS_THRESHOLD) {
            return Optional.of(new FraudRuleHitResults(
                    FraudRule.MULTIPLE_FAILED_ATTEMPTS,
                    "User had " + failedCount + " failed transaction(s) in the last " +
                            WINDOW_MINUTES + " minutes"
            ));
        }

        return Optional.empty();
    }
}