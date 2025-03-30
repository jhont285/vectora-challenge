package com.vectora.transaction_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String CREATED_MESSAGE_QUEUE = "created-message-queue";

    @Bean
    public Queue myQueue() {
        return new Queue(CREATED_MESSAGE_QUEUE, true); // Durable queue
    }
}