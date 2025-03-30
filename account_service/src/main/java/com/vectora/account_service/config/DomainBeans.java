package com.vectora.account_service.config;

import com.vectora.account_service.application.service.account.CreateAccountService;
import com.vectora.account_service.application.service.account.GetAccountService;
import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import com.vectora.account_service.domain.port.in.UseCase;
import com.vectora.account_service.domain.port.out.AccountPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeans {

    @Bean("createAccountUseCase")
    public UseCase<AccountFields, Account> createAccountUseCase(AccountPort accountPort) {
        return new CreateAccountService(accountPort);
    }

    @Bean("getAccountUseCase")
    public UseCase<Integer, Account> getAccountUseCase(AccountPort accountPort) {
        return new GetAccountService(accountPort);
    }
}
