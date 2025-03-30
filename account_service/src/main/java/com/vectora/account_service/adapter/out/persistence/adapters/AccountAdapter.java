package com.vectora.account_service.adapter.out.persistence.adapters;


import com.vectora.account_service.adapter.out.persistence.repository.AccountRepository;
import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import com.vectora.account_service.domain.port.out.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.vectora.account_service.adapter.out.persistence.mappers.AccountMapper.ACCOUNT_MAPPER;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountPort {
    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> get(int id) {
        return accountRepository.findById(id)
                .map(ACCOUNT_MAPPER::toAccount);
    }

    @Override
    public Account create(AccountFields accountFields) {
        var accountEntity = ACCOUNT_MAPPER.toAccountEntity(accountFields);
        var accountEntitySaved = accountRepository.save(accountEntity);
        return ACCOUNT_MAPPER.toAccount(accountEntitySaved);
    }
}
