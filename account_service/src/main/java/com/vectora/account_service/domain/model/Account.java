package com.vectora.account_service.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

public record Account(int id,
                      @JsonProperty("nombre")
                      String name,
                      @JsonProperty("saldo")
                      int balance) implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar un UID único

    @Builder
    public Account {
    }
}
