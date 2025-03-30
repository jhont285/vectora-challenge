package com.vectora.transaction_service.adapter.out.async_message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.vectora.transaction_service.config.RabbitMQConfig.CREATED_MESSAGE_QUEUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RabbitMQAdapterTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private RabbitMQAdapter rabbitMQAdapter;

    @Test
    void shouldSendMessageSuccessfully() throws JsonProcessingException {
        // Given
        TestMessage message = new TestMessage("Test Content");
        String jsonMessage = "{\"content\":\"Test Content\"}";

        when(objectMapper.writeValueAsString(message)).thenReturn(jsonMessage);

        // When
        rabbitMQAdapter.sendMessage(message);

        // Then
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(rabbitTemplate, times(1)).convertAndSend(eq(CREATED_MESSAGE_QUEUE), messageCaptor.capture());
        assertEquals(jsonMessage, messageCaptor.getValue());
    }

    @Test
    void shouldHandleJsonProcessingException() throws JsonProcessingException {
        // Given
        TestMessage message = new TestMessage("Invalid Message");

        when(objectMapper.writeValueAsString(message)).thenThrow(new JsonProcessingException("Error") {
        });

        // When / Then
        try {
            rabbitMQAdapter.sendMessage(message);
        } catch (Exception e) {
            // Verify that exception is thrown
            assertEquals("Error", e.getMessage());
        }

        verify(rabbitTemplate, never()).convertAndSend(anyString(), anyString());
    }

    // Sample message class for testing
    record TestMessage(String content) {
    }
}