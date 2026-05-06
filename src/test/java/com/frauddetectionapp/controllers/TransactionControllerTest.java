package com.frauddetectionapp.controllers;

import com.frauddetectionapp.dto.transaction.TransactionRequest;
import com.frauddetectionapp.dto.transaction.TransactionResponse;
import com.frauddetectionapp.services.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionRequest request;
    private TransactionResponse response;

    @BeforeEach
    void setUp() {
        request = new TransactionRequest("john", "15000", null, "REF123");
        response = new TransactionResponse(1L, "john", "15000", null, null, null, null, null, "REF123", null);
    }

    @Test
    @DisplayName("Should create transaction successfully")
    void shouldCreateTransaction() {
        when(transactionService.createTransaction(any(TransactionRequest.class))).thenReturn(response);

        ResponseEntity<TransactionResponse> result = transactionController.createTransaction(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("REF123", result.getBody().getReference());
    }

    @Test
    @DisplayName("Should return all transactions")
    void shouldGetAllTransactions() {
        when(transactionService.getAllTransactions()).thenReturn(List.of(response));

        List<TransactionResponse> transactions = transactionController.getAllTransactions();

        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
    }
}