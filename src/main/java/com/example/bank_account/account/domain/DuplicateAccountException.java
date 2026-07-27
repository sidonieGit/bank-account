package com.example.bank_account.account.domain;

public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException(String ownerName) {
        super("A bank account already exists for owner: " + ownerName);
    }
}
