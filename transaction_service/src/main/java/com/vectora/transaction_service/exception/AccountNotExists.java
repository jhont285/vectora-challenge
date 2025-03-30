package com.vectora.transaction_service.exception;

public class AccountNotExists extends RuntimeException {
    public AccountNotExists(String message) {
        super(message);
    }
}
