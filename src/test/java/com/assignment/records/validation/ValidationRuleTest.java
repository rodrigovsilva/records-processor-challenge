package com.assignment.records.validation;

import com.assignment.records.model.TransactionRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ValidationRuleTest {

    @Test
    public void testValidate_UniqueReferenceRule() {
        TransactionRecord record = new TransactionRecord("123", "123456", "test", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0"));
        TransactionRecord repeated = new TransactionRecord("123", "123456", "test", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0"));

        var ruleUniqueReference = new RuleUniqueReference();

        Assertions.assertTrue(ruleUniqueReference.validate(record));
        Assertions.assertFalse(ruleUniqueReference.validate(repeated));
    }

    @Test
    public void testValidate_CorrectEndBalance() {
        TransactionRecord record = new TransactionRecord("123", "123456", "test", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0"));
        TransactionRecord record2 = new TransactionRecord("3", "Account3", "Description3", new BigDecimal("300.0"), new BigDecimal("50.0"), new BigDecimal("450.0"));

        var ruleEndCurrentBalance = new RuleEndCurrentBalance();

        Assertions.assertTrue(ruleEndCurrentBalance.validate(record));
        Assertions.assertFalse(ruleEndCurrentBalance.validate(record2));
    }
}