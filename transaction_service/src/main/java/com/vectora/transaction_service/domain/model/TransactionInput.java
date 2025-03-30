package com.vectora.transaction_service.domain.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

public record TransactionInput(@PositiveOrZero
                               int fromAccount,
                               @PositiveOrZero
                               int toAccount,
                               @Positive
                               int balance) {
    @Builder
    public TransactionInput {
    }
}
