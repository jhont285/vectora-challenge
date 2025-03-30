package com.vectora.transaction_service.config;

import com.vectora.transaction_service.application.service.CreateTransaction;
import com.vectora.transaction_service.application.service.GetTransactions;
import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.TransactionState;
import com.vectora.transaction_service.domain.port.in.UseCase;
import com.vectora.transaction_service.domain.port.out.AccountPort;
import com.vectora.transaction_service.domain.port.out.AsyncMessagePort;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainsBeans {

    @Bean("transactionCreateUseCase")
    public UseCase<TransactionInput, TransactionState> transactionCreateUseCase(AccountPort accountPort,
                                                                                TransactionPort transactionPort,
                                                                                AsyncMessagePort messagePort) {
        return new CreateTransaction(accountPort, transactionPort, messagePort);
    }

    @Bean("getTransactionsUseCase")
    public UseCase<Integer, List<Transaction>> getTransactions(TransactionPort transactionPort) {
        return new GetTransactions(transactionPort);
    }

}
