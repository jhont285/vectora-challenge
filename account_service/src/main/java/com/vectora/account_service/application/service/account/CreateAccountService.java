package com.vectora.account_service.application.service.account;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import com.vectora.account_service.domain.port.in.UseCase;
import com.vectora.account_service.domain.port.out.AccountPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class CreateAccountService implements UseCase<AccountFields, Account> {
    private final AccountPort accountPort;

    @Override
    public Account execute(AccountFields item) {
        log.info("Creating item with name {}", item.name());
        return accountPort.create(item);
    }
}
