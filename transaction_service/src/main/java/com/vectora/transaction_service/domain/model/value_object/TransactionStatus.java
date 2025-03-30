package com.vectora.transaction_service.domain.model.value_object;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {
    SUCCESS("success"),
    FAILED("failed");

    private final String value;

    @JsonValue // Serialize enum as string
    public String getValue() {
        return value;
    }

    @JsonCreator // Deserialize from string
    public static TransactionStatus fromValue(String value) {
        return Arrays.stream(TransactionStatus.values())
                .filter(state -> state.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status: " + value));
    }
}
