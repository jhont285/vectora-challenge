package com.vectora.transaction_service.adapter.out.rest.account.adapter;

import com.vectora.transaction_service.adapter.out.rest.account.client.AccountClient;
import com.vectora.transaction_service.domain.port.out.AccountPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountPort {
    private final AccountClient accountClient;

    @Override
    public boolean existsTransaction(int id) {
        try {
            var account = accountClient.getAccount(id);
            return Objects.nonNull(account);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }
}
