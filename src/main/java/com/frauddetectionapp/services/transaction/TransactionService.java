package com.frauddetectionapp.services.transaction;

import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.dto.transaction.TransactionRequest;
import com.frauddetectionapp.dto.transaction.TransactionResponse;
import com.frauddetectionapp.dto.transaction.TransactionsMapper;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import com.frauddetectionapp.services.flaggedtransaction.FlaggedTransactionService;
import com.frauddetectionapp.services.flaggedtransaction.FraudEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionsMapper mapper;
    private final TransactionRepository transactionRepository;
    private final FlaggedTransactionService flaggedTransactionService;
    private final FraudEngine fraudEngine;

    public TransactionResponse createTransaction(TransactionRequest  transactionRequest){

        Transaction saved = transactionRepository.save(mapper.toEntity(transactionRequest));


        fraudEngine.evaluate(saved).ifPresent(flaggedTransactionService::saveFlag);

        log.info("Created new transaction: {}", saved.getTransactionId());


        return mapper.toResponseDTO(saved);
    }

    public List<TransactionResponse> getAllTransactions() {
        log.info("getting all transactions");
        return transactionRepository.findAllTransactions()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<TransactionResponse> getTransactionsByUser(String useriD) {
        log.info("getting transaction by Id {}", useriD);
        return transactionRepository.findTransactionsByUserId(useriD)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
            }

    public List<TransactionResponse> getTransactionsByChanell(String channelCategory) {

        log.info("Getting transaction by channel {}", channelCategory);

        return transactionRepository.findByChannel(channelCategory)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<TransactionResponse> getTransactionsByPaymentCategory(String paymentCategory) {

        log.info("get transaction by payment category {}", paymentCategory);

        return transactionRepository.findTransactionsByUserPaymentCategory(paymentCategory)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }
}
