package com.vectora.account_service.domain.port.out;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;

import java.util.Optional;

public interface AccountPort {
    Optional<Account> get(int id);

    Account create(AccountFields item);
}
