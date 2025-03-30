package com.vectora.transaction_service.domain.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionInputTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldFailOnNegativeValues() {
        TransactionInput invalidInput = new TransactionInput(-1, -2, -100);

        Set<ConstraintViolation<TransactionInput>> violations = validator.validate(invalidInput);

        assertFalse(violations.isEmpty(), "Validation should fail for negative values");
    }

    @Test
    void shouldPassOnValidValues() {
        TransactionInput validInput = new TransactionInput(1, 2, 100);

        Set<ConstraintViolation<TransactionInput>> violations = validator.validate(validInput);

        assertTrue(violations.isEmpty(), "Validation should pass for valid values");
    }

    @Test
    void shouldFailWhenFromAccountIsNegative() {
        TransactionInput invalidInput = new TransactionInput(-1, 2, 100);

        Set<ConstraintViolation<TransactionInput>> violations = validator.validate(invalidInput);

        assertFalse(violations.isEmpty(), "Validation should fail when fromAccount is negative");
    }

    @Test
    void shouldFailWhenToAccountIsNegative() {
        TransactionInput invalidInput = new TransactionInput(1, -2, 100);

        Set<ConstraintViolation<TransactionInput>> violations = validator.validate(invalidInput);

        assertFalse(violations.isEmpty(), "Validation should fail when toAccount is negative");
    }

    @Test
    void shouldFailWhenBalanceIsNegative() {
        TransactionInput invalidInput = new TransactionInput(1, 2, -100);

        Set<ConstraintViolation<TransactionInput>> violations = validator.validate(invalidInput);

        assertFalse(violations.isEmpty(), "Validation should fail when balance is negative");
    }
}