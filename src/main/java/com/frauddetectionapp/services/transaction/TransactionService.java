package com.frauddetectionapp.services.transaction;

import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.dto.transaction.TransactionRequest;
import com.frauddetectionapp.dto.transaction.TransactionResponse;
import com.frauddetectionapp.dto.transaction.TransactionsMapper;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import com.frauddetectionapp.services.exceptions.BusinessException;
import com.frauddetectionapp.services.flaggedtransaction.FlaggedTransactionService;
import com.frauddetectionapp.services.flaggedtransaction.FraudEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionsMapper mapper;
    private final TransactionRepository transactionRepository;
    private final FlaggedTransactionService flaggedTransactionService;
    private final FraudEngine fraudEngine;


    public TransactionResponse createTransaction(TransactionRequest  transactionRequest){

        if (transactionRequest.getAmount() == null || transactionRequest.getAmount().compareTo(String.valueOf(BigDecimal.ZERO)) <= 0) {
            throw new BusinessException("Transaction amount must be greater than zero",
                    HttpStatus.BAD_REQUEST);
        }
        if (transactionRequest.getReference().isEmpty()) {
            throw new BusinessException("reference cannot be empty");
        }

        Transaction saved = transactionRepository.save(mapper.toEntity(transactionRequest));


        fraudEngine.evaluate(saved).ifPresent(flaggedTransactionService::saveFlag);

        log.info("Created new transaction: {}", saved.getTransactionId());


        return mapper.toResponseDTO(saved);
    }

    public Optional<TransactionResponse> getTransactionById(Long Id) {
        log.info("getting transaction By Id transactions");
        return transactionRepository.findById(Id)
                .map(mapper::toResponseDTO);
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
