package com.frauddetectionapp.services.flaggedtransaction.fraudrules;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DuplicateReferenceRule implements FraudRuleInterface {

    private final TransactionRepository transactionRepository;

    @Override
    public Optional<FraudRuleHitResults> evaluate(Transaction transaction) {
        String reference = transaction.getReference();

        if (reference == null || reference.isBlank()) return Optional.empty();

        boolean isDuplicate = transactionRepository
                .existsByReferenceAndTransactionIdNot(reference, transaction.getTransactionId());

        if (isDuplicate) {
            log.warn("DUPLICATE_REFERENCE triggered — transactionId={} reference={}",
                    transaction.getTransactionId(), reference);
            return Optional.of(new FraudRuleHitResults(
                    FraudRule.DUPLICATE_REFERENCE,
                    "Reference " + reference + " has already been used"
            ));
        }
        return Optional.empty();
    }
}