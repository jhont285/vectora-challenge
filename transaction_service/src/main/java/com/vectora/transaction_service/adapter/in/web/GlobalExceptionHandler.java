package com.vectora.transaction_service.adapter.in.web;

import com.vectora.transaction_service.exception.AccountNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotExists.class)
    public ResponseEntity<String> handleAccountNotExistsException(AccountNotExists ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}