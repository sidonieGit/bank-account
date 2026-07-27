package com.example.bank_account.account.domain;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(BigDecimal balance, BigDecimal amount) {
        super("Insufficient funds: balance is " + balance + " but requested amount is " + amount);
    }
}
