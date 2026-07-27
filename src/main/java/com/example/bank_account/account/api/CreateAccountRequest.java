package com.example.bank_account.account.api;

import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank
        @Pattern(
                regexp = "^[A-Z]{2}[0-9]{10,16}$",
                message = "Account number must start with 2 uppercase letters followed by 10 to 16 digits"
        )
        String accountNumber,

        @NotBlank
        String ownerName,

        @NotNull
        @Positive
        BigDecimal balance,

        @NotNull
        Currency currency,

        @NotNull
        AccountType accountType
) {}
