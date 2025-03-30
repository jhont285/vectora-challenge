package com.vectora.transaction_service.application.service;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.port.in.UseCase;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetTransactions implements UseCase<Integer, List<Transaction>> {
    private final TransactionPort transactionPort;

    @Override
    public List<Transaction> execute(Integer id) {
        return transactionPort.getByAccountId(id);
    }
}
