package com.vectora.transaction_service.adapter.out.persistence.adapters;

import com.vectora.transaction_service.adapter.out.persistence.repository.TransactionRepository;
import com.vectora.transaction_service.domain.model.Transaction;
import com.vectora.transaction_service.domain.model.TransactionInput;
import com.vectora.transaction_service.domain.model.value_object.TransactionStatus;
import com.vectora.transaction_service.domain.port.out.TransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vectora.transaction_service.adapter.out.persistence.mapper.TransactionEntityMapper.TRANSACTION_ENTITY_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionAdapter implements TransactionPort {
    private static final String LIST_ACCOUNTS = "listAccounts";
    private final TransactionRepository transactionRepository;
    private final CacheManager cacheManager;

    @Override
    @Cacheable(value = LIST_ACCOUNTS, key = "#transactionId") // Caches the result
    public List<Transaction> getByAccountId(int transactionId) {
        return transactionRepository.findAllByFromAccountOrToAccount(transactionId, transactionId).stream().map(TRANSACTION_ENTITY_MAPPER::toTransaction).toList();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = LIST_ACCOUNTS, key = "#transactionInput.fromAccount"),
            @CacheEvict(value = LIST_ACCOUNTS, key = "#transactionInput.toAccount")
    })
    public Transaction save(TransactionInput transactionInput, TransactionStatus status) {
        log.info("Saving transaction");
        var transactionEntity = TRANSACTION_ENTITY_MAPPER.toTransactionEntity(transactionInput, status);
        var transactionEntitySaved = transactionRepository.save(transactionEntity);

        log.info("Saved successfully transaction with id {} and status {}", transactionEntitySaved.getId(), transactionEntitySaved.getStatus());

        return TRANSACTION_ENTITY_MAPPER.toTransaction(transactionEntitySaved);
    }
}
