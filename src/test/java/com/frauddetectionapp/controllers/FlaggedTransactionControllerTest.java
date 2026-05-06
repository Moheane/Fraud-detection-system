package com.frauddetectionapp.controllers;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.services.flaggedtransaction.FlaggedTransactionService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlaggedTransactionControllerTest {

    @Mock
    private FlaggedTransactionService flaggedTransactionService;

    @InjectMocks
    private FlaggedTransactionController controller;

    private FlaggedTransaction flagged;

    @BeforeEach
    void setUp() {
        flagged = FlaggedTransaction.builder()
                .flagId(100L)
                .amount("25000")
                .reason("High amount")
                .build();
    }

    @Test
    @DisplayName("Should return all flagged transactions")
    void shouldGetAllFlaggedTransactions() {
        when(flaggedTransactionService.getAllFlaggedTransactions()).thenReturn(List.of(flagged));

        List<FlaggedTransaction> result = controller.getAllflaggedTransactions();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should get flagged transaction by ID")
    void shouldGetByTransactionId() {
        when(flaggedTransactionService.getByTransactionId(100L)).thenReturn(Optional.of(flagged));

        ResponseEntity<Optional<FlaggedTransaction>> response = controller.getByTransactionId(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
    }

    @Test
    @DisplayName("Should delete flagged transaction")
    void shouldDeleteTransaction() {
        doNothing().when(flaggedTransactionService).deleteTransaction(100L);

        ResponseEntity<String> response = controller.deleteByTransactionId(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(flaggedTransactionService, times(1)).deleteTransaction(100L);
    }
}