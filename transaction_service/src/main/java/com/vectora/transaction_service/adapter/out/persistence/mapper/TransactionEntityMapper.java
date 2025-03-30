package com.vectora.transaction_service.adapter.out.persistence.mapper;

import com.vectora.transaction_service.adapter.out.persistence.model.TransactionEntity;
import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionEntityMapper {
    TransactionEntityMapper TRANSACTION_ENTITY_MAPPER = Mappers.getMapper(TransactionEntityMapper.class);

    @Mapping(target = "status", source = "state", qualifiedByName = "mapTransactionStatusToString")
    TransactionEntity toTransactionEntity(TransactionInput transactionInput, TransactionStatus state);

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStringToTransactionStatus")
    Transaction toTransaction(TransactionEntity transactionEntity);

    @Named("mapTransactionStatusToString")
    default String mapTransactionStatusToString(TransactionStatus status) {
        return status != null ? status.name() : null;
    }

    @Named("mapStringToTransactionStatus")
    default TransactionStatus mapStringToTransactionStatus(String status) {
        return status != null ? TransactionStatus.fromValue(status) : null;
    }
}
