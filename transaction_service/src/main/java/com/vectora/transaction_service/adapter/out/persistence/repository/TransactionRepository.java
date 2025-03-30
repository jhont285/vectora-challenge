package com.vectora.transaction_service.adapter.out.persistence.repository;

import com.vectora.transaction_service.adapter.out.persistence.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByFromAccountOrToAccount(int fromAccount, int toAccount);
}
