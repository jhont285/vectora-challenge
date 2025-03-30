package com.vectora.account_service.domain.model;

import lombok.Builder;

import java.io.Serializable;

public record Account(int id,
                      String name,
                      int balance) implements Serializable {
    private static final long serialVersionUID = 1L; // Agregar un UID único

    @Builder
    public Account {
    }
}
