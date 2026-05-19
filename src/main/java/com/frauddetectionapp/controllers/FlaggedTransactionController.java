package com.frauddetectionapp.controllers;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.services.flaggedtransaction.FlaggedTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Flagged Transactions", description = "Flagged Transactions APIs")
@AllArgsConstructor
@RestController
@RequestMapping("/api/flaggedtransaction")
class FlaggedTransactionController {

    private final FlaggedTransactionService flaggedTransactionService;

    @GetMapping("/ping")
    public String getPing() {
        return "Flagged API Works fine";
    }

    @Operation(summary = "Get Flagged Transactions", description = "Get All Flagged Transactions")
    @GetMapping("/")
    public List<FlaggedTransaction> getAllflaggedTransactions() {

        return flaggedTransactionService.getAllFlaggedTransactions();
    }


    @Operation(summary = "Get Flagged Transaction", description = "Get Flagged Transaction By Id")
    @GetMapping("/id/{transactionId}")
    public ResponseEntity<Optional<FlaggedTransaction>> getByTransactionId(@PathVariable Long transactionId) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getByTransactionId(transactionId));
    }

    @Operation(summary = "Delete Flagged Transaction", description = "Delete Flagged Transaction By Id")
    @DeleteMapping("/id/{transactionId}")
    public ResponseEntity<String> deleteByTransactionId(@PathVariable Long transactionId) {
        flaggedTransactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction with transactionId " + transactionId + " deleted successfully");
    }

    @Operation(summary = "Get Flagged Transaction By Location", description = "Get Flagged Transaction By Location")
    @GetMapping("/location/{location}")
    public ResponseEntity<List<FlaggedTransaction>> getByLocation(@PathVariable String location) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getByLocation(location));
    }

    @Operation(summary = "Get Flagged Transaction By Status", description = "Get Flagged Transaction By Status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FlaggedTransaction>> getByStatus(@PathVariable String status) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getFlaggedTransactionByStatus(status));
    }

    @Operation(summary = "Get Flagged Transaction By Channel", description = "Get Flagged Transaction By Channel")
    @GetMapping("/channel/{channel}")
    public ResponseEntity<List<FlaggedTransaction>> getByChannel(@PathVariable String channel) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getByChannel(channel));
    }

    @Operation(summary = "Get Flagged Transaction By Payment Category", description = "Get Flagged Transaction By Payment category")
    @GetMapping("/payment/{paymentCategory}")
    public ResponseEntity<List<FlaggedTransaction>> getByPaymentCategory(@PathVariable String paymentCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getByPaymentCategory(paymentCategory));
    }

    @Operation(summary = "Get Flagged Transaction By FraudRule", description = "Get Flagged Transaction By FraudRule")
    @GetMapping("/fraudRule/{fraudRule}")
    public ResponseEntity<List<FlaggedTransaction>> getByFraudRule(@PathVariable String fraudRule) {
        return ResponseEntity.status(HttpStatus.OK).body(flaggedTransactionService.getByFraudRule(fraudRule));
    }





}
