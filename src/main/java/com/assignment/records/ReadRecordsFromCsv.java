package com.assignment.records;

import com.assignment.records.exception.CanNotProcessFileException;
import com.assignment.records.model.TransactionRecord;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadRecordsFromCsv is a class for reading records data from CSV files.
 */
public class ReadRecordsFromCsv implements ReadRecords {

    private static final Logger logger = LoggerFactory.getLogger(ReadRecordsFromCsv.class);

    @Override
    public List<TransactionRecord> from(Path inputFile) {
        logger.info("Reading CSV file: {}", inputFile.getFileName().getFileName());

        List<TransactionRecord> transactionRecords = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputFile.toFile()))) {
            List<String[]> records = reader.readAll();
            int recordCount = records.size(); // Total records read

            logger.info("CSV Records read: {}", recordCount);
            logger.info("CSV file read successfully");

            // Process the records as needed
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                // Implement record processing logic here
                logger.debug("Processing record: {}", (Object) record);
                transactionRecords.add(parseRecord(record));
            }
        } catch (Exception e) {
            throw CanNotProcessFileException.failedParsingFile(inputFile.toString(), e);
        }

        logger.info("%d CSV Records processed successfully".formatted(transactionRecords.size()));

        return transactionRecords;
    }

    /**
     * Parse the record and create a TransactionRecord object.
     *
     * @param record The record as String array to parse
     * @return TransactionRecord object
     */
    private TransactionRecord parseRecord(String[] record) {
        return new TransactionRecord(record[0],
                record[1],
                record[2],
                new BigDecimal(record[3]),
                new BigDecimal(record[4]),
                new BigDecimal(record[5]));
    }
}
