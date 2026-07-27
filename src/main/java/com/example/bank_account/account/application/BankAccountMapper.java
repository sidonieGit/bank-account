package com.example.bank_account.account.application;

import com.example.bank_account.account.api.AccountResponse;
import com.example.bank_account.account.api.CreateAccountRequest;
import com.example.bank_account.account.api.UpdateAccountRequest;
import com.example.bank_account.account.domain.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    BankAccount toEntity(CreateAccountRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(UpdateAccountRequest request, @MappingTarget BankAccount account);

    AccountResponse toResponse(BankAccount account);
}
