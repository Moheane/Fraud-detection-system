package com.frauddetectionapp.controllers;

import com.frauddetectionapp.Entities.account.Account;

import com.frauddetectionapp.services.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
class AccountController {

    private  final AccountService accountService;

    @GetMapping("/ping")
    public String getPing() {
        return "Account API Works fine";
    }

    @Operation(summary = "Create account", description = "Create a new account")
    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account response = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @Operation(summary = "Get Accounts", description = "Get all accounts")
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {

        return accountService.getAllAccounts();
    }

}
