package com.vectora.transaction_service.domain.port.out;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;

import java.util.List;

public interface TransactionPort {
    List<Transaction> getByAccountId(int transactionId);

    Transaction save(TransactionInput transactionInput, TransactionStatus state);
}
