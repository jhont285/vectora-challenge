package com.vectora.transaction_service.adapter.out.rest.account.model;

import lombok.Builder;

public record Account(int id,
                      String name,
                      int balance) {

    @Builder
    public Account {
    }
}
