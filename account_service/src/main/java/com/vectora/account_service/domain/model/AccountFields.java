package com.vectora.account_service.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

public record AccountFields(@NotBlank
                            String name,
                            @PositiveOrZero
                            int openingBalance) {

    @Builder
    public AccountFields {
    }
}
