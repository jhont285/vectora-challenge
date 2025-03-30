package com.vectora.transaction_service.adapter.out.async_message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vectora.transaction_service.domain.port.out.AsyncMessagePort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.vectora.transaction_service.config.RabbitMQConfig.CREATED_MESSAGE_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQAdapter implements AsyncMessagePort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public <T> void sendMessage(T message) {
        log.info("Sending message to created queue");
        rabbitTemplate.convertAndSend(CREATED_MESSAGE_QUEUE, mapper.writeValueAsString(message));
    }
}
