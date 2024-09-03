package com.assignment.records;

import com.assignment.records.model.TransactionRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportInvalidRecordsFromParsedDataTest {

    private ReportInvalidRecordsFromParsedData reportInvalidRecords;

    @BeforeEach
    public void setUp() {
        // Initialize the class under test with mock validation rules
        reportInvalidRecords = new ReportInvalidRecordsFromParsedData();
    }

    @Test
    public void testOn_GivenNoTransactions_WhenCalled_ThenReturnsEmptyList() {
        List<TransactionRecord> result = reportInvalidRecords.on(Collections.emptyList());

        assertEquals(0, result.size());
    }

    @Test
    public void testOn_GivenValidTransactions_WhenAllRulesPass_ThenReturnsEmptyList() {
        TransactionRecord validTransaction1 = new TransactionRecord("1", "Account1", "Description1", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0"));
        TransactionRecord validTransaction2 = new TransactionRecord("2", "Account2", "Description2", new BigDecimal("200.0"), new BigDecimal("-50.0"), new BigDecimal("150.0"));

        List<TransactionRecord> result = reportInvalidRecords.on(Arrays.asList(validTransaction1, validTransaction2));

        assertEquals(0, result.size());
    }

    @Test
    public void testOn_GivenInvalidTransactions_WhenSomeRulesFail_ThenReturnsInvalidTransactions() {
        TransactionRecord uniqueTransaction = new TransactionRecord("1", "Account1", "Description1", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0"));
        TransactionRecord repeatedTransaction = new TransactionRecord("1", "Account2", "Description2", new BigDecimal("200.0"), new BigDecimal("-50.0"), new BigDecimal("150.0"));
        TransactionRecord wrongBalance = new TransactionRecord("3", "Account3", "Description3", new BigDecimal("300.0"), new BigDecimal("50.0"), new BigDecimal("450.0"));

        List<TransactionRecord> result = reportInvalidRecords.on(Arrays.asList(uniqueTransaction, repeatedTransaction, wrongBalance));

        assertEquals(2, result.size());
        assertEquals(Arrays.asList(repeatedTransaction, wrongBalance), result);
    }
}