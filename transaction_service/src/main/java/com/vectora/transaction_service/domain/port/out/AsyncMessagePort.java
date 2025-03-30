package com.vectora.transaction_service.domain.port.out;

public interface AsyncMessagePort {
    <T> void sendMessage(T message);
}
