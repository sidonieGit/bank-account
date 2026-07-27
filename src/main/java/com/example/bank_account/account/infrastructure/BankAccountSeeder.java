package com.example.bank_account.account.infrastructure;

import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import com.example.bank_account.account.domain.BankAccount;
import com.example.bank_account.account.domain.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Profile({"local", "docker"})
public class BankAccountSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BankAccountSeeder.class);

    private final BankAccountRepository repository;

    public BankAccountSeeder(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info("[seed] Collection already contains data – skipping.");
            return;
        }
        repository.saveAll(accounts());
        log.info("[seed] Inserted 20 bank accounts.");
    }

    private List<BankAccount> accounts() {
        Instant now = Instant.now();
        return List.of(
            build("FR001234567890", "Alice Martin",       "2450.75",  Currency.EURO, AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(180, ChronoUnit.DAYS), now.minus(5,   ChronoUnit.DAYS)),
            build("US002345678901", "Bob Thompson",       "15200.00", Currency.USD,  AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(365, ChronoUnit.DAYS), now.minus(10,  ChronoUnit.DAYS)),
            build("ES003456789012", "Carlos Rodriguez",   "890.50",   Currency.EURO, AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(90,  ChronoUnit.DAYS), now.minus(2,   ChronoUnit.DAYS)),
            build("GB004567890123", "Diana Chen",         "32000.00", Currency.GBP,  AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(500, ChronoUnit.DAYS), now.minus(30,  ChronoUnit.DAYS)),
            build("FR005678901234", "Emmanuel Dupont",    "1200.00",  Currency.EURO, AccountType.CHECKING, AccountStatus.SUSPENDED,  now.minus(200, ChronoUnit.DAYS), now.minus(15,  ChronoUnit.DAYS)),
            build("US006789012345", "Fatima Al-Hassan",   "5670.25",  Currency.USD,  AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(120, ChronoUnit.DAYS), now.minus(1,   ChronoUnit.DAYS)),
            build("GB007890123456", "George Williams",    "8900.00",  Currency.GBP,  AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(730, ChronoUnit.DAYS), now.minus(60,  ChronoUnit.DAYS)),
            build("DE008901234567", "Helena Novak",       "42000.00", Currency.EURO, AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(1000,ChronoUnit.DAYS), now.minus(90,  ChronoUnit.DAYS)),
            build("US009012345678", "Ibrahim Osei",       "0.00",     Currency.USD,  AccountType.CHECKING, AccountStatus.CLOSED,     now.minus(400, ChronoUnit.DAYS), now.minus(45,  ChronoUnit.DAYS)),
            build("FR010123456789", "Julia Santos",       "3400.50",  Currency.EURO, AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(60,  ChronoUnit.DAYS), now.minus(3,   ChronoUnit.DAYS)),
            build("GB011234567890", "Kevin O'Brien",      "12500.00", Currency.GBP,  AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(300, ChronoUnit.DAYS), now.minus(20,  ChronoUnit.DAYS)),
            build("DE012345678901", "Laura Schmidt",      "7800.00",  Currency.EURO, AccountType.SAVINGS,  AccountStatus.SUSPENDED,  now.minus(250, ChronoUnit.DAYS), now.minus(7,   ChronoUnit.DAYS)),
            build("US013456789012", "Mohammed Al-Farsi",  "9200.75",  Currency.USD,  AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(150, ChronoUnit.DAYS), now.minus(4,   ChronoUnit.DAYS)),
            build("FR014567890123", "Nadia Kowalski",     "560.00",   Currency.EURO, AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(30,  ChronoUnit.DAYS), now.minus(1,   ChronoUnit.DAYS)),
            build("US015678901234", "Oliver Johnson",     "28000.00", Currency.USD,  AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(800, ChronoUnit.DAYS), now.minus(50,  ChronoUnit.DAYS)),
            build("FR016789012345", "Patricia Leclerc",   "18500.00", Currency.EURO, AccountType.SAVINGS,  AccountStatus.ACTIVE,     now.minus(450, ChronoUnit.DAYS), now.minus(12,  ChronoUnit.DAYS)),
            build("GB017890123456", "Rashid Patel",       "3200.00",  Currency.GBP,  AccountType.CHECKING, AccountStatus.SUSPENDED,  now.minus(220, ChronoUnit.DAYS), now.minus(8,   ChronoUnit.DAYS)),
            build("US018901234567", "Sophia Anderson",    "6750.00",  Currency.USD,  AccountType.CHECKING, AccountStatus.ACTIVE,     now.minus(75,  ChronoUnit.DAYS), now.minus(6,   ChronoUnit.DAYS)),
            build("DE019012345678", "Thomas Muller",      "0.00",     Currency.EURO, AccountType.CHECKING, AccountStatus.CLOSED,     now.minus(600, ChronoUnit.DAYS), now.minus(180, ChronoUnit.DAYS)),
            build("US020123456789", "Victoria Lee",       "450.00",   Currency.USD,  AccountType.SAVINGS,  AccountStatus.CLOSED,     now.minus(350, ChronoUnit.DAYS), now.minus(100, ChronoUnit.DAYS))
        );
    }

    private BankAccount build(String accountNumber, String ownerName, String balance,
                              Currency currency, AccountType accountType, AccountStatus status,
                              Instant createdAt, Instant updatedAt) {
        return BankAccount.builder()
                .accountNumber(accountNumber)
                .ownerName(ownerName)
                .balance(new BigDecimal(balance))
                .currency(currency)
                .accountType(accountType)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
