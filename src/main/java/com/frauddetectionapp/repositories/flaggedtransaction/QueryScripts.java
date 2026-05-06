package com.frauddetectionapp.repositories.flaggedtransaction;

import org.springframework.stereotype.Component;

@Component
public class QueryScripts {

    public String findByLocation() {
        return "SELECT t FROM FlaggedTransaction t WHERE t.location = :locationCategory";
    }

    public String findByChannel() {
        return "SELECT t FROM FlaggedTransaction t WHERE t.channel = :channelCategory";
    }

    public String findByStatus() {
        return "SELECT t FROM FlaggedTransaction t WHERE t.status = :statusCategory";
    }

    public String findByPaymentCategory() {
        return "SELECT t FROM FlaggedTransaction t WHERE t.paymentCategory = :paymentCategory";
    }
}