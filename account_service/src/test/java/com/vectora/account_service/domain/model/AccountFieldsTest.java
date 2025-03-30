package com.vectora.account_service.domain.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountFieldsTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void shouldPassValidationWithValidData() {
        AccountFields validAccount = new AccountFields("John Doe", 5000);
        Set<ConstraintViolation<AccountFields>> violations = validator.validate(validAccount);
        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void shouldFailValidationWhenNameIsEmpty() {
        AccountFields invalidAccount = new AccountFields("", 5000);
        Set<ConstraintViolation<AccountFields>> violations = validator.validate(invalidAccount);
        assertFalse(violations.isEmpty(), "Validation should fail for empty name");
    }

    @Test
    void shouldFailValidationWhenOpeningBalanceIsNegative() {
        AccountFields invalidAccount = new AccountFields("John Doe", -100);
        Set<ConstraintViolation<AccountFields>> violations = validator.validate(invalidAccount);
        assertFalse(violations.isEmpty(), "Validation should fail for negative balance");
    }

    @Test
    void shouldFailValidationWhenNameIsNull() {
        AccountFields invalidAccount = new AccountFields(null, 5000);
        Set<ConstraintViolation<AccountFields>> violations = validator.validate(invalidAccount);
        assertFalse(violations.isEmpty(), "Validation should fail for null name");
    }
}