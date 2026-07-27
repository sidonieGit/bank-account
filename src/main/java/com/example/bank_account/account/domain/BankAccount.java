package com.example.bank_account.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "bank_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    private String id;

    private String accountNumber;

    private String ownerName;

    private BigDecimal balance;

    private Currency currency;

    private AccountType accountType;

    private AccountStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    @Version
    private Long version;
}
