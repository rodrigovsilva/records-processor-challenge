package com.assignment.records.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionRecordTest {
    static Stream<Arguments> testCalculatedBalance() {
        return Stream.of(
                Arguments.of(new TransactionRecord("123", "123456", "test", new BigDecimal("100.0"), new BigDecimal("10.0"), new BigDecimal("110.0")), new BigDecimal("110.0")),
                Arguments.of(new TransactionRecord("123", "123456", "test", new BigDecimal("100.0"), new BigDecimal("-10.0"), new BigDecimal("90.0")), new BigDecimal("90.0")));
    }

    @ParameterizedTest
    @MethodSource
    public void testCalculatedBalance(TransactionRecord transactionRecord, BigDecimal expectedBalance) {
        assertEquals(0, transactionRecord.calculatedBalance().compareTo(expectedBalance));
    }
}
