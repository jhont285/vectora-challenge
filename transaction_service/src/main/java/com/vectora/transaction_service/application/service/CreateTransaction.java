package com.vectora.transaction_service.application.service;

import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.TransactionState;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import com.vectora.transaction_service.domain.port.in.UseCase;
import com.vectora.transaction_service.domain.port.out.AccountPort;
import com.vectora.transaction_service.domain.port.out.AsyncMessagePort;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import com.vectora.transaction_service.exception.AccountNotExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

import static com.vectora.transaction_service.domain.model.mappers.TransactionMapper.TRANSACTION_MAPPER;

@Slf4j
@RequiredArgsConstructor
public class CreateTransaction implements UseCase<TransactionInput, TransactionState> {
    private final AccountPort accountPort;
    private final TransactionPort transactionPort;
    private final AsyncMessagePort messagePort;

    @Override
    public TransactionState execute(TransactionInput transactionInput) throws AccountNotExists {
        validateAccount(transactionInput.fromAccount(), transactionInput,
                () -> "FromAccount doesn't exists with id %d".formatted(transactionInput.fromAccount()));
        validateAccount(transactionInput.toAccount(), transactionInput,
                () -> "ToAccount doesn't exists with id %d".formatted(transactionInput.toAccount()));

        var transactionCreated = transactionPort.save(transactionInput, TransactionStatus.SUCCESS);
        messagePort.sendMessage(transactionCreated);
        return TRANSACTION_MAPPER.toTransactionState(transactionCreated);
    }

    private void validateAccount(int transactionId,
                                 TransactionInput transactionInput,
                                 Supplier<String> message) {

        log.info("Validating if account with id {} exists", transactionId);
        var existsToAccount = accountPort.existsTransaction(transactionId);
        if (!existsToAccount) {
            transactionPort.save(transactionInput, TransactionStatus.FAILED);
            throw new AccountNotExists(message.get().formatted(transactionId));
        }
    }
}
