package com.frauddetectionapp.controllers;

import com.frauddetectionapp.dto.transaction.TransactionRequest;
import com.frauddetectionapp.dto.transaction.TransactionResponse;
import com.frauddetectionapp.services.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transactions", description = "Transactions APIs")
@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/ping")
    public String getPing() {
        return "API Works fine";
    }

    @Operation(summary = "Create Transaction", description = "Get New Transaction")
    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request){
        TransactionResponse response = transactionService.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };
    @Operation(summary = "Get All Transaction", description = "Get All Transaction")
    @GetMapping("/transactions")
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @Operation(summary = "Get Transaction by Id", description = "Get Transaction By Id")
    @GetMapping("/transactions/get_by_user/{user_id}")
    public List<TransactionResponse> getTransactionsByUserId(@PathVariable String user_id) {
        return transactionService.getTransactionsByUser(user_id);
    }

    @Operation(summary = "Get Transaction by Channel", description = "Get Transaction By Channel")
    @GetMapping("/transactions/channel/{channel_category}")
    public List<TransactionResponse> getTransactionByChannelCategory(@PathVariable String channel_category) {
        return transactionService.getTransactionsByChanell(channel_category);
    }

    @Operation(summary = "Get Transaction by payment category", description = "Get Transaction By payment category")
    @GetMapping("/transactions/payment/{payment_category}")
    public List<TransactionResponse> getTransactionByPaymentCategory(@PathVariable String payment_category) {
        return transactionService.getTransactionsByPaymentCategory(payment_category);
    }
}