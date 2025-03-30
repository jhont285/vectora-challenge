package com.vectora.account_service.adapter;

import com.vectora.account_service.adapter.out.persistence.adapters.AccountAdapter;
import com.vectora.account_service.adapter.out.persistence.model.AccountEntity;
import com.vectora.account_service.adapter.out.persistence.repository.AccountRepository;
import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

    public static final AccountEntity ACCOUNT_ENTITY = AccountEntity.builder()
            .id(1)
            .name("John Doe")
            .balance(5_000)
            .build();
    private final Account MOCK_ACCOUNT = Account.builder()
            .id(1)
            .name("John Doe")
            .balance(5_000)
            .build();
    private final AccountFields MOCK_ACCOUNT_FIELDS = AccountFields.builder()
            .name("John Doe")
            .openingBalance(5_000)
            .build();


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountAdapter accountAdapter;

    @Test
    void shouldReturnAccountWhenFound() {
        // Arrange
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(ACCOUNT_ENTITY));

        // Act
        Optional<Account> result = accountAdapter.get(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(MOCK_ACCOUNT, result.get());
        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnEmptyWhenAccountNotFound() {
        // Arrange
        when(accountRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Account> result = accountAdapter.get(1);

        // Assert
        assertFalse(result.isPresent());
        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    void shouldCreateAndReturnAccount() {
        // Arrange
        when(accountRepository.save(any())).thenReturn(ACCOUNT_ENTITY);

        // Act
        Account result = accountAdapter.create(MOCK_ACCOUNT_FIELDS);

        // Assert
        assertNotNull(result);
        assertEquals(MOCK_ACCOUNT, result);
        verify(accountRepository, times(1)).save(any());
    }
}