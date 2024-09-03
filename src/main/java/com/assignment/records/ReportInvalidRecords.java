package com.assignment.records;

import com.assignment.records.model.TransactionRecord;

import java.util.List;

/**
 * ReportInvalidRecords is a functional interface for reporting invalid records.
 */
public interface ReportInvalidRecords {
    List<TransactionRecord> on(List<TransactionRecord> transactionRecords);
}
