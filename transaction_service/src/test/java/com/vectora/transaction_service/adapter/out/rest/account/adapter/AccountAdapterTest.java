package com.vectora.transaction_service.adapter.out.rest.account.adapter;

import com.vectora.transaction_service.adapter.out.rest.account.client.AccountClient;
import com.vectora.transaction_service.adapter.out.rest.account.model.Account;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

    @Mock
    private AccountClient accountClient;

    @InjectMocks
    private AccountAdapter accountAdapter;

    @Test
    void shouldReturnTrueWhenAccountExists() {
        // Given
        int accountId = 123;
        when(accountClient.getAccount(accountId)).thenReturn(Account.builder().build()); // Simulate a valid account

        // When
        boolean result = accountAdapter.existsTransaction(accountId);

        // Then
        assertTrue(result);
        verify(accountClient, times(1)).getAccount(accountId);
    }

    @Test
    void shouldReturnFalseWhenAccountDoesNotExist() {
        // Given
        int accountId = 123;
        when(accountClient.getAccount(accountId)).thenThrow(FeignException.BadRequest.class);

        // When
        boolean result = accountAdapter.existsTransaction(accountId);

        // Then
        assertFalse(result);
        verify(accountClient, times(1)).getAccount(accountId);
    }
}