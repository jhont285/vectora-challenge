package com.vectora.transaction_service.application.service;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetTransactionsTest {

    @Mock
    private TransactionPort transactionPort;

    @InjectMocks
    private GetTransactions getTransactions;

    @Test
    void shouldReturnTransactionsWhenValidIdIsProvided() {
        // Given
        int accountId = 1;
        List<Transaction> mockTransactions = Arrays.asList(
                new Transaction(1, 1, 2, TransactionStatus.FAILED, 100, LocalDateTime.now()),
                new Transaction(2, 1, 2, TransactionStatus.SUCCESS, 200, LocalDateTime.now())
        );

        when(transactionPort.getByAccountId(accountId)).thenReturn(mockTransactions);

        // When
        List<Transaction> result = getTransactions.execute(accountId);

        // Then
        assertEquals(2, result.size());
        assertEquals(mockTransactions, result);
        verify(transactionPort).getByAccountId(accountId);
    }

    @Test
    void shouldReturnEmptyListWhenNoTransactionsFound() {
        // Given
        int accountId = 2;
        when(transactionPort.getByAccountId(accountId)).thenReturn(List.of());

        // When
        List<Transaction> result = getTransactions.execute(accountId);

        // Then
        assertEquals(0, result.size());
        verify(transactionPort).getByAccountId(accountId);
    }
}