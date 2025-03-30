package com.vectora.account_service.application.service.account;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.model.AccountFields;
import com.vectora.account_service.domain.port.out.AccountPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountServiceTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private CreateAccountService createAccountService;

    private final Integer ACCOUNT_ID = 1;
    private final Account MOCK_ACCOUNT = Account.builder()
            .id(ACCOUNT_ID)
            .name("John Doe")
            .salary(5_000)
            .build();
    private final AccountFields MOCK_ACCOUNT_FIELDS = AccountFields.builder()
            .name("John Doe")
            .openingBalance(5_000)
            .build();

    @Test
    void shouldCreateAndReturnAccount() {
        // Arrange
        when(accountPort.create(any())).thenReturn(MOCK_ACCOUNT);

        // Act
        Account result = createAccountService.execute(MOCK_ACCOUNT_FIELDS);

        // Assert
        assertNotNull(result);
        assertEquals(MOCK_ACCOUNT, result);
        verify(accountPort).create(MOCK_ACCOUNT_FIELDS);
    }
}