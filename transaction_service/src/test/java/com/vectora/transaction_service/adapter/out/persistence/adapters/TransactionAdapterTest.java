package com.vectora.transaction_service.adapter.out.persistence.adapters;

import com.vectora.transaction_service.adapter.out.persistence.model.TransactionEntity;
import com.vectora.transaction_service.adapter.out.persistence.repository.TransactionRepository;
import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionAdapterTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionAdapter transactionAdapter;

    @Test
    void shouldReturnTransactionsWhenFound() {
        // Given
        int accountId = 1;
        var transactionEntity = TransactionEntity.builder().build();
        when(transactionRepository.findAllByFromAccountOrToAccount(accountId, accountId))
                .thenReturn(List.of(transactionEntity));

        // When
        List<Transaction> transactions = transactionAdapter.getByAccountId(accountId);

        // Then
        assertFalse(transactions.isEmpty());
        verify(transactionRepository, times(1)).findAllByFromAccountOrToAccount(accountId, accountId);
    }

    @Test
    void shouldReturnEmptyListWhenNoTransactionsFound() {
        // Given
        int accountId = 1;
        when(transactionRepository.findAllByFromAccountOrToAccount(accountId, accountId))
                .thenReturn(Collections.emptyList());

        // When
        List<Transaction> transactions = transactionAdapter.getByAccountId(accountId);

        // Then
        assertTrue(transactions.isEmpty());
        verify(transactionRepository, times(1)).findAllByFromAccountOrToAccount(accountId, accountId);
    }

    @Test
    void shouldSaveTransactionSuccessfully() {
        // Given
        TransactionInput transactionInput = TransactionInput.builder().build(); // Mocked input data
        TransactionStatus status = TransactionStatus.SUCCESS;
        TransactionEntity savedTransactionEntity = new TransactionEntity();
        savedTransactionEntity.setId(1);
        savedTransactionEntity.setStatus(status.getValue());

        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(savedTransactionEntity);

        // When
        Transaction transaction = transactionAdapter.save(transactionInput, status);

        // Then
        assertNotNull(transaction);
        assertEquals(1, transaction.id());
        assertEquals(status, transaction.status());
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }
}