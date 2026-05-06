package com.frauddetectionapp.services.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRule;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.PaymentCategory;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.services.flaggedtransaction.fraudrules.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudEngine {

    private final HighAmountRule highAmountRule;
    private final SuspiciousLocationRule suspiciousLocationRule;
    private final DuplicateReferenceRule duplicateReferenceRule;
    private final MultipleFailedAttemptsRule multipleFailedAttemptsRule;
    private final RapidTransactionRule rapidTransactionRule;

    public Optional<FlaggedTransaction> evaluate(Transaction transaction) {
        log.info("Evaluating fraud rules for transactionId={}", transaction.getTransactionId());

        List<FraudRuleHitResults> flags = List.of(
                        highAmountRule,
                        suspiciousLocationRule,
                        duplicateReferenceRule,
                        multipleFailedAttemptsRule,
                        rapidTransactionRule
                )
                .stream()
                .map(rule -> rule.evaluate(transaction))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (flags.isEmpty()) {
            log.info("transactionId={} is clean", transaction.getTransactionId());
            return Optional.empty();
        }

        return Optional.of(buildCombinedFlag(transaction, flags));


    }


    private FlaggedTransaction buildCombinedFlag(Transaction t, List<FraudRuleHitResults> flags) {

        // "HIGH_AMOUNT, RAPID_TRANSACTIONS"
         String triggeredRules = flags.stream()
                .map(FraudRuleHitResults::getRuleName)
                .collect(Collectors.joining(", "));

        // "Amount 15000 exceeds threshold | 5 transactions in 60 seconds"
        String combinedReason = flags.stream()
                .map(FraudRuleHitResults::getReason)
                .collect(Collectors.joining(" | "));

        return FlaggedTransaction.builder()
                .amount(t.getAmount())
                .currency(t.getCurrency())
                .timeStamp(t.getTimeStamp() )
                .location(t.getLocation())
                .method(t.getMethod())
                .channel(t.getChannel())
                .paymentCategory(t.getPaymentCategory() != null
                        ? PaymentCategory.valueOf(t.getPaymentCategory().name())  // ← enum → String
                        : null)
                .reference(t.getReference())
                .triggeredRules(triggeredRules)
                .reason(combinedReason)
                .transactionStatus(t.getStatus())
                .build();

    }

}
