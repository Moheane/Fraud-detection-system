package com.frauddetectionapp.services.transaction;

import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.dto.transaction.TransactionRequest;
import com.frauddetectionapp.dto.transaction.TransactionResponse;
import com.frauddetectionapp.dto.transaction.TransactionsMapper;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import com.frauddetectionapp.services.flaggedtransaction.FlaggedTransactionService;
import com.frauddetectionapp.services.flaggedtransaction.FraudEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionsMapper mapper;
    @Mock
    private FlaggedTransactionService flaggedTransactionService;
    @Mock
    private FraudEngine fraudEngine;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionRequest request;
    private Transaction transaction;
    private TransactionResponse response;

    @BeforeEach
    void setUp() {
        request = new TransactionRequest("testuser", "12000", null, "REF456");
        transaction = new Transaction();
        transaction.setTransactionId(1L);
        response = new TransactionResponse();
    }

    @Test
    void shouldCreateTransactionAndEvaluateFraud() {
        when(mapper.toEntity(any())).thenReturn(transaction);
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(mapper.toResponseDTO(any())).thenReturn(response);
        when(fraudEngine.evaluate(any())).thenReturn(Optional.empty());

        TransactionResponse result = transactionService.createTransaction(request);

        assertNotNull(result);
        verify(transactionRepository).save(any());
        verify(fraudEngine).evaluate(any());
    }
}