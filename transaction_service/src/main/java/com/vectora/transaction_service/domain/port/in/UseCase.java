package com.vectora.transaction_service.domain.port.in;

public interface UseCase<T, R> {
    R execute(T item);
}
