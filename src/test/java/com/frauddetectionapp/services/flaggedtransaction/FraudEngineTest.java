package com.frauddetectionapp.services.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.flaggedtransaction.FraudRuleHitResults;
import com.frauddetectionapp.Entities.transaction.Location;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.services.flaggedtransaction.fraudrules.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FraudEngineTest {

    @Mock private HighAmountRule highAmountRule;
    @Mock private SuspiciousLocationRule suspiciousLocationRule;
    @Mock private DuplicateReferenceRule duplicateReferenceRule;
    @Mock private MultipleFailedAttemptsRule multipleFailedAttemptsRule;
    @Mock private RapidTransactionRule rapidTransactionRule;

    @InjectMocks
    private FraudEngine fraudEngine;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setTransactionId(999L);
        transaction.setAmount("15000");
        transaction.setLocation(Location.NORTH_KOREA);
    }

    @Test
    @DisplayName("Should flag transaction when rules are triggered")
    void shouldFlagTransactionWhenRulesTriggered() {
        when(highAmountRule.evaluate(any())).thenReturn(Optional.of(new FraudRuleHitResults("HIGH_AMOUNT", "High amount")));
        when(suspiciousLocationRule.evaluate(any())).thenReturn(Optional.of(new FraudRuleHitResults("SUSPICIOUS_LOCATION", "Bad location")));

        Optional<FlaggedTransaction> result = fraudEngine.evaluate(transaction);

        assertTrue(result.isPresent());
        assertTrue(result.get().getTriggeredRules().contains("HIGH_AMOUNT"));
    }

    @Test
    @DisplayName("Should return empty when no rules are triggered")
    void shouldReturnEmptyWhenNoRulesTriggered() {
        when(highAmountRule.evaluate(any())).thenReturn(Optional.empty());
        when(suspiciousLocationRule.evaluate(any())).thenReturn(Optional.empty());
        // ... other rules

        Optional<FlaggedTransaction> result = fraudEngine.evaluate(transaction);

        assertTrue(result.isEmpty());
    }
}