package com.assignment.records;

import java.nio.file.Path;

/**
 * ProcessRecords is an interface for processing record files.
 */
public interface ProcessRecords {
    void from(Path inputFile);
}
