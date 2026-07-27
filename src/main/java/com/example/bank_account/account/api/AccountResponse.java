package com.example.bank_account.account.api;

import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.BankAccount;
import com.example.bank_account.account.domain.Currency;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponse(
        String id,
        String accountNumber,
        String ownerName,
        BigDecimal balance,
        Currency currency,
        AccountType accountType,
        AccountStatus status,
        Instant createdAt,
        Instant updatedAt
) {
    public static AccountResponse from(BankAccount account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getCurrency(),
                account.getAccountType(),
                account.getStatus(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
