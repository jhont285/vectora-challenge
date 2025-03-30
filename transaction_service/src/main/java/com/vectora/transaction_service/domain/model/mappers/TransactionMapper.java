package com.vectora.transaction_service.domain.model.mappers;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionState;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper TRANSACTION_MAPPER = Mappers.getMapper(TransactionMapper.class);


    TransactionState toTransactionState(Transaction transaction);
}
