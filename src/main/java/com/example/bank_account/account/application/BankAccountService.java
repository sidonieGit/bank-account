package com.example.bank_account.account.application;

import com.example.bank_account.account.api.AccountResponse;
import com.example.bank_account.account.api.CreateAccountRequest;
import com.example.bank_account.account.api.PatchAccountRequest;
import com.example.bank_account.account.api.UpdateAccountRequest;
import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {

    AccountResponse create(CreateAccountRequest request);

    AccountResponse getById(String id);

    Page<AccountResponse> list(AccountStatus status, AccountType accountType, Pageable pageable);

    AccountResponse update(String id, UpdateAccountRequest request);

    AccountResponse patch(String id, PatchAccountRequest request);

    void softDelete(String id);
}
