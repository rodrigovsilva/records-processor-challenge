package com.assignment.records;

import com.assignment.records.model.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * ProcessRecordsFromFile is a class for processing records from a file.
 */
public class ProcessRecordsFromFile implements ProcessRecords {

    private static final Logger logger = LoggerFactory.getLogger(ProcessRecordsFromFile.class);

    private final ReportInvalidRecords reportInvalidRecords;

    private final GenerateReport generateReport;

    public ProcessRecordsFromFile() {
        this.reportInvalidRecords = new ReportInvalidRecordsFromParsedData();
        this.generateReport = new GenerateReportWithInvalidTransactions();
    }

    /**
     * Load the transactions from files and validate them.
     * <p>As a result of this processing, we generate a report with validation errors.</p>
     *
     * @param inputFile the input file to process
     */
    @Override
    public void from(Path inputFile) {
        ReadRecords readRecords = FileReaderFactory.getReader(inputFile);
        List<TransactionRecord> transactionRecords = readRecords.from(inputFile);

        logger.info("Processing {} records from file: {}", transactionRecords.size(), inputFile);

        List<TransactionRecord> invalidRecords = reportInvalidRecords.on(transactionRecords);

        if (!invalidRecords.isEmpty()) {
            Path reportFile = getReportFileName(inputFile);
            generateReport.from(reportFile, invalidRecords);
            logger.info("Report generated at: {}", reportFile);
        } else {
            logger.info("No invalid records found.");
        }
    }

    /**
     * Get the report file name from the input file.
     *
     * @param inputFile the input file
     * @return the report file name
     */
    private Path getReportFileName(Path inputFile) {
        String reportFileName = inputFile.getFileName().toString();
        int lastDotIndex = reportFileName.lastIndexOf('.');

        if (lastDotIndex != -1) {
            reportFileName = reportFileName.substring(0, lastDotIndex);
        }

        return inputFile.getParent().resolve("%s-report.csv".formatted(reportFileName));
    }
}
