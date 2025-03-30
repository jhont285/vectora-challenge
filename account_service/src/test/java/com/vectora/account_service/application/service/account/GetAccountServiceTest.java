package com.vectora.account_service.application.service.account;

import com.vectora.account_service.domain.model.Account;
import com.vectora.account_service.domain.port.out.AccountPort;
import com.vectora.account_service.exception.ElementNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountServiceTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private GetAccountService getAccountService;

    private final Integer ACCOUNT_ID = 1;
    private final Account MOCK_ACCOUNT = Account.builder()
            .id(ACCOUNT_ID)
            .name("John Doe")
            .salary(5_000)
            .build();

    @Test
    void shouldReturnAccountWhenFound() {
        // Arrange
        when(accountPort.get(ACCOUNT_ID)).thenReturn(Optional.of(MOCK_ACCOUNT));

        // Act
        Account result = getAccountService.execute(ACCOUNT_ID);

        // Assert
        assertNotNull(result);
        assertEquals(MOCK_ACCOUNT, result);
        verify(accountPort, times(1)).get(ACCOUNT_ID);
    }

    @Test
    void shouldThrowElementNotFoundWhenAccountDoesNotExist() {
        // Arrange
        when(accountPort.get(ACCOUNT_ID)).thenReturn(Optional.empty());

        // Act & Assert
        ElementNotFound exception = assertThrows(ElementNotFound.class, () -> getAccountService.execute(ACCOUNT_ID));

        assertEquals("account not found with id 1", exception.getMessage());
        verify(accountPort, times(1)).get(ACCOUNT_ID);
    }
}