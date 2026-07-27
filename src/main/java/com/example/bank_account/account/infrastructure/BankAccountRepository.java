package com.example.bank_account.account.infrastructure;

import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    List<BankAccount> findByStatus(AccountStatus status);

    Page<BankAccount> findByStatus(AccountStatus status, Pageable pageable);

    Page<BankAccount> findByAccountType(AccountType accountType, Pageable pageable);

    Page<BankAccount> findByStatusAndAccountType(AccountStatus status, AccountType accountType, Pageable pageable);
}
