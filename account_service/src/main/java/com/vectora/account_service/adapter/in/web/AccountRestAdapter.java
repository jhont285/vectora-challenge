package com.vectora.account_service.adapter.in.web;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import com.vectora.account_service.domain.port.in.UseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountRestAdapter {
    private final UseCase<AccountFields, Account> createAccountUseCase;
    private final UseCase<Integer, Account> getAccountUseCase;

    public AccountRestAdapter(@Qualifier("createAccountUseCase") UseCase<AccountFields, Account> createAccountUseCase,
                              @Qualifier("getAccountUseCase") UseCase<Integer, Account> getAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
    }

    @GetMapping("{id}")
    public Account getAccount(@Valid @PathVariable @PositiveOrZero int id) {
        return getAccountUseCase.execute(id);
    }

    @PostMapping
    public Account createAccount(@Valid @RequestBody AccountFields accountFields) {
        return createAccountUseCase.execute(accountFields);
    }
}
