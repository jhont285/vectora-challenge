package com.vectora.account_service.adapter.out.persistence.repository;

import com.vectora.account_service.adapter.out.persistence.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
