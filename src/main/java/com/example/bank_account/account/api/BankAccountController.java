package com.example.bank_account.account.api;

import com.example.bank_account.account.application.BankAccountService;
import com.example.bank_account.account.domain.AccountStatus;
import com.example.bank_account.account.domain.AccountType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Bank Accounts", description = "Operations for managing bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body or constraint violation"),
            @ApiResponse(responseCode = "409", description = "Account number already exists")
    })
    public AccountResponse create(@Valid @RequestBody CreateAccountRequest request) {
        return bankAccountService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a bank account by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public AccountResponse getById(
            @Parameter(description = "Account document ID") @PathVariable String id) {
        return bankAccountService.getById(id);
    }

    @GetMapping
    @Operation(summary = "List bank accounts with optional filters and pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated list returned")
    })
    public Page<AccountResponse> list(
            @Parameter(description = "Filter by account status") @RequestParam(required = false) AccountStatus status,
            @Parameter(description = "Filter by account type") @RequestParam(required = false) AccountType accountType,
            @ParameterObject Pageable pageable) {
        return bankAccountService.list(status, accountType, pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Fully update a bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public AccountResponse update(
            @Parameter(description = "Account document ID") @PathVariable String id,
            @Valid @RequestBody UpdateAccountRequest request) {
        return bankAccountService.update(id, request);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a bank account (e.g. status change)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account patched"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public AccountResponse patch(
            @Parameter(description = "Account document ID") @PathVariable String id,
            @RequestBody PatchAccountRequest request) {
        return bankAccountService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Soft-delete a bank account (sets status to CLOSED, never removes the document)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account closed"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public void delete(
            @Parameter(description = "Account document ID") @PathVariable String id) {
        bankAccountService.softDelete(id);
    }
}
