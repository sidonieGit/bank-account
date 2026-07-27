package com.example.bank_account.account.api;

import com.example.bank_account.account.domain.AccountStatus;

public record PatchAccountRequest(
        AccountStatus status
) {}
