package com.vectora.transaction_service.domain.model;

import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

public record Transaction(int id,
                          int fromAccount,
                          int toAccount,
                          TransactionStatus status,
                          int balance,
                          LocalDateTime date) implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar un UID único

    @Builder
    public Transaction {
    }
}
