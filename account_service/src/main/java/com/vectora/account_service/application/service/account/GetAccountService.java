package com.vectora.account_service.application.service.account;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.port.in.UseCase;
import com.vectora.account_service.domain.port.out.AccountPort;
import com.vectora.account_service.exception.ElementNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
@RequiredArgsConstructor
public class GetAccountService implements UseCase<Integer, Account> {
    private final AccountPort accountPort;

    @Override
    @Cacheable(value = "accounts", key = "#id", unless = "#result == null")
    public Account execute(Integer id) {
        log.info("Getting account with id: {}", id);
        return accountPort.get(id)
                .orElseThrow(() -> new ElementNotFound("account not found with id %d".formatted(id)));
    }
}
