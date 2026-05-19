package com.frauddetectionapp.Entities.flaggedtransaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum FraudRule {
    HIGH_AMOUNT(25),            // transaction amount exceeds threshold
    RAPID_TRANSACTIONS(25),     // too many transactions in a short window
    SUSPICIOUS_LOCATION(15),    // transaction from a flagged location
    DUPLICATE_REFERENCE(15),   // same reference used more than once
    MULTIPLE_FAILED_ATTEMPTS(20);

    private final int points;

    FraudRule(int points) {
        this.points = points;
    }
}
