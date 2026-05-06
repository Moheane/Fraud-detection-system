package com.frauddetectionapp.services.account;

import com.frauddetectionapp.Entities.account.Account;
import com.frauddetectionapp.Entities.flaggedtransaction.FlaggedTransaction;
import com.frauddetectionapp.Entities.transaction.Transaction;
import com.frauddetectionapp.dto.transaction.TransactionsMapper;
import com.frauddetectionapp.repositories.account.AccountRepository;
import com.frauddetectionapp.repositories.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionsMapper mapper;


    public List<Account> getAllAccounts(){
        assert accountRepository != null;
        return accountRepository.findAll()
                .stream()
                .toList();
    }

    public Account createAccount(Account account){
        Account saved = accountRepository.save(account);

        return saved;
    }
}
