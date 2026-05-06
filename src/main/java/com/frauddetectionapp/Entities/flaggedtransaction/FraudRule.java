package com.frauddetectionapp.Entities.flaggedtransaction;


public enum FraudRule {
    HIGH_AMOUNT,            // transaction amount exceeds threshold
    RAPID_TRANSACTIONS,     // too many transactions in a short window
    SUSPICIOUS_LOCATION,    // transaction from a flagged location
    DUPLICATE_REFERENCE,    // same reference used more than once
    MULTIPLE_FAILED_ATTEMPTS// mu
}
