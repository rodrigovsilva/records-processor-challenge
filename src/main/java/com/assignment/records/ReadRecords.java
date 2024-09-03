package com.assignment.records;

import com.assignment.records.model.TransactionRecord;

import java.nio.file.Path;
import java.util.List;

/**
 * ReadRecords is an interface for processing record files.
 */
public interface ReadRecords {
    List<TransactionRecord> from(Path inputFile);
}
