package com.vectora.account_service.domain.port.in;

public interface UseCase<T,R>{
    R execute(T item);
}
