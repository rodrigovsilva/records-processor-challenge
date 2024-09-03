package com.assignment.records;

import com.assignment.records.model.TransactionRecord;

import java.nio.file.Path;
import java.util.List;

/**
 * GenerateReport is an interface that is used to generate a report from a list of transaction records.
 */
public interface GenerateReport {
    void from(Path file, List<TransactionRecord> transactionRecords);
}
