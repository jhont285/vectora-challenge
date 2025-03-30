package com.vectora.transaction_service.adapter.in.web;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.TransactionState;
import com.vectora.transaction_service.domain.port.in.UseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionRestAdapter {
    private final UseCase<TransactionInput, TransactionState> transactionCreateUseCase;
    private final UseCase<Integer, List<Transaction>> getTransactionsUseCase;


    public TransactionRestAdapter(@Qualifier("transactionCreateUseCase") UseCase<TransactionInput, TransactionState> transactionCreateUseCase,
                                  @Qualifier("getTransactionsUseCase") UseCase<Integer, List<Transaction>> getTransactionsUseCase) {
        this.transactionCreateUseCase = transactionCreateUseCase;
        this.getTransactionsUseCase = getTransactionsUseCase;
    }

    @GetMapping("{accountId}")
    public List<Transaction> getTransaction(@Valid @PositiveOrZero @PathVariable int accountId) {
        return getTransactionsUseCase.execute(accountId);
    }

    @PostMapping
    public TransactionState createTransaction(@Valid @RequestBody TransactionInput transactionInput) {
        return transactionCreateUseCase.execute(transactionInput);
    }
}
