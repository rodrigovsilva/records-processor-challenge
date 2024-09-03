package com.assignment.records;

import com.assignment.records.exception.CanNotCreateReportFileException;
import com.assignment.records.model.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * GenerateReportWithInvalidTransactions is an interface used to generate a report from a list of invalid transaction records.
 */
public class GenerateReportWithInvalidTransactions implements GenerateReport {

    private static final Logger logger = LoggerFactory.getLogger(GenerateReportWithInvalidTransactions.class);

    /**
     * Generate a report with invalid transactions.
     *
     * @param file               the file to write the report to
     * @param transactionRecords the list of transaction records
     */
    @Override
    public void from(Path file, List<TransactionRecord> transactionRecords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            // write header
            writer.write("Reference,Description\n");

            logger.info("========== List of invalid records ==========");
            logger.info("Reference\tDescription");
            // write each transaction record
            for (TransactionRecord record : transactionRecords) {
                logger.info("{}\t{}", record.reference(), record.description());
                writer.write(String.format("%s,%s\n", record.reference(), record.description()));
            }

            logger.info("CSV report generated successfully at: {}", file.getFileName().getFileName());
        } catch (IOException e) {
            throw CanNotCreateReportFileException.failedGeneratingFile(file.toString(), e);
        }
    }
}
