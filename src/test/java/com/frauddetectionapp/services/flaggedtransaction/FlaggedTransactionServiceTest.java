package com.frauddetectionapp.services.flaggedtransaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.repositories.flaggedtransaction.FlaggedTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlaggedTransactionServiceTest {

    @Mock
    private FlaggedTransactionRepository repository;

    @InjectMocks
    private FlaggedTransactionService service;

    private FlaggedTransaction flagged;

    @BeforeEach
    void setUp() {
        flagged = FlaggedTransaction.builder()
                .flagId(500L)
                .amount("25000")
                .reason("High amount detected")
                .triggeredRules("HIGH_AMOUNT")
                .build();
    }

    @Test
    @DisplayName("Should return all flagged transactions")
    void shouldGetAllFlaggedTransactions() {
        when(repository.findAll()).thenReturn(List.of(flagged));

        List<FlaggedTransaction> result = service.getAllFlaggedTransactions();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should find flagged transaction by ID")
    void shouldGetByTransactionId() {
        when(repository.findById(500L)).thenReturn(Optional.of(flagged));

        Optional<FlaggedTransaction> result = service.getByTransactionId(500L);

        assertTrue(result.isPresent());
        assertEquals("25000", result.get().getAmount());
    }

    @Test
    @DisplayName("Should delete flagged transaction")
    void shouldDeleteTransaction() {
        doNothing().when(repository).deleteById(500L);

        service.deleteTransaction(500L);

        verify(repository, times(1)).deleteById(500L);
    }

    @Test
    @DisplayName("Should save flagged transaction")
    void shouldSaveFlag() {
        service.saveFlag(flagged);

        verify(repository, times(1)).save(flagged);
    }
}