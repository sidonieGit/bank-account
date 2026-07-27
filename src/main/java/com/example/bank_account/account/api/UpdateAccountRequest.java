package com.example.bank_account.account.api;

import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateAccountRequest(

        @NotBlank
        String ownerName,

        @NotNull
        @Positive
        BigDecimal balance,

        @NotNull
        Currency currency,

        @NotNull
        AccountType accountType,

        @NotNull
        AccountStatus status
) {}
