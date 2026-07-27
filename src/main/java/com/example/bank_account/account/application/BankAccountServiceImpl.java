package com.example.bank_account.account.application;

import com.example.bank_account.account.api.AccountResponse;
import com.example.bank_account.account.api.CreateAccountRequest;
import com.example.bank_account.account.api.PatchAccountRequest;
import com.example.bank_account.account.api.UpdateAccountRequest;
import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.AccountNotFoundException;
import com.example.bank_account.account.domain.BankAccount;
import com.example.bank_account.account.domain.DuplicateAccountException;
import com.example.bank_account.account.infrastructure.BankAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final BankAccountMapper mapper;

    public BankAccountServiceImpl(BankAccountRepository repository, BankAccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountResponse create(CreateAccountRequest request) {
        if (repository.findByAccountNumber(request.accountNumber()).isPresent()) {
            throw new DuplicateAccountException(request.accountNumber());
        }
        BankAccount account = mapper.toEntity(request);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(Instant.now());
        account.setUpdatedAt(Instant.now());
        return mapper.toResponse(repository.save(account));
    }

    @Override
    public AccountResponse getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public Page<AccountResponse> list(AccountStatus status, AccountType accountType, Pageable pageable) {
        Page<BankAccount> page;
        if (status != null && accountType != null) {
            page = repository.findByStatusAndAccountType(status, accountType, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else if (accountType != null) {
            page = repository.findByAccountType(accountType, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return page.map(mapper::toResponse);
    }

    @Override
    public AccountResponse update(String id, UpdateAccountRequest request) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        mapper.updateEntity(request, account);
        account.setUpdatedAt(Instant.now());
        return mapper.toResponse(repository.save(account));
    }

    @Override
    public AccountResponse patch(String id, PatchAccountRequest request) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        if (request.status() != null) {
            account.setStatus(request.status());
        }
        account.setUpdatedAt(Instant.now());
        return mapper.toResponse(repository.save(account));
    }

    @Override
    public void softDelete(String id) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.setStatus(AccountStatus.CLOSED);
        account.setUpdatedAt(Instant.now());
        repository.save(account);
    }
}
