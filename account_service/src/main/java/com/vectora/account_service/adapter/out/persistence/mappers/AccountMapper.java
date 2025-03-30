package com.vectora.account_service.adapter.out.persistence.mappers;

import com.vectora.account_service.adapter.out.persistence.model.AccountEntity;
import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountEntity accountEntity);

    @Mapping(source = "openingBalance", target = "balance")
    AccountEntity toAccountEntity(AccountFields accountFields);
}
