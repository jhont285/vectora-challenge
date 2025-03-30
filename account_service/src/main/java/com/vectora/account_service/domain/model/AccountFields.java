package com.vectora.account_service.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

public record AccountFields(@NotBlank
                            @JsonProperty("nombre")
                            String name,
                            @PositiveOrZero
                            @JsonProperty("saldoInicial")
                            int openingBalance) {

    @Builder
    public AccountFields {
    }
}
