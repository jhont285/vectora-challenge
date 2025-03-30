package com.vectora.transaction_service.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import lombok.Builder;

public record TransactionState(@JsonProperty("transactionId")
                               int id,
                               TransactionStatus status) {
    @Builder
    public TransactionState {
    }
}
