package com.example.bank_account.account.domain;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String id) {
        super("Bank account not found with id: " + id);
    }
}
