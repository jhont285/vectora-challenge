package com.vectora.transaction_service.application.service;

import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.TransactionState;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import com.vectora.transaction_service.domain.port.out.AccountPort;
import com.vectora.transaction_service.domain.port.out.AsyncMessagePort;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import com.vectora.transaction_service.exception.AccountNotExists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionTest {

    @Mock
    private AccountPort accountPort;

    @Mock
    private TransactionPort transactionPort;

    @Mock
    private AsyncMessagePort messagePort;

    @InjectMocks
    private CreateTransaction createTransaction;

    @Test
    void shouldCreateTransactionSuccessfully() {
        // Arrange
        TransactionInput transactionInput = new TransactionInput(1, 2, 100);
        when(accountPort.existsTransaction(1)).thenReturn(false);
        when(accountPort.existsTransaction(2)).thenReturn(false);
        when(transactionPort.save(transactionInput, TransactionStatus.SUCCESS))
                .thenReturn(Transaction.builder().fromAccount(1).toAccount(2).status(TransactionStatus.SUCCESS).build());
        // Act
        TransactionState result = createTransaction.execute(transactionInput);

        // Assert
        assertNotNull(result);
        assertEquals(TransactionStatus.SUCCESS, result.status());
        verify(transactionPort).save(transactionInput, TransactionStatus.SUCCESS);
        verify(messagePort).sendMessage(any());
    }

    @Test
    void shouldThrowExceptionWhenFromAccountDoesNotExist() {
        // Arrange
        TransactionInput transactionInput = new TransactionInput(1, 2, 100);
        when(accountPort.existsTransaction(1)).thenReturn(true);

        // Act & Assert
        AccountNotExists exception = assertThrows(AccountNotExists.class,
                () -> createTransaction.execute(transactionInput));

        assertTrue(exception.getMessage().contains("FromAccount doesn't exists"));
        verify(transactionPort).save(transactionInput, TransactionStatus.FAILED);
    }

    @Test
    void shouldThrowExceptionWhenToAccountDoesNotExist() {
        // Arrange
        TransactionInput transactionInput = new TransactionInput(1, 2, 100);
        when(accountPort.existsTransaction(1)).thenReturn(false);
        when(accountPort.existsTransaction(2)).thenReturn(true);

        // Act & Assert
        AccountNotExists exception = assertThrows(AccountNotExists.class,
                () -> createTransaction.execute(transactionInput));

        assertTrue(exception.getMessage().contains("ToAccount doesn't exists"));
        verify(transactionPort).save(transactionInput, TransactionStatus.FAILED);
    }
}