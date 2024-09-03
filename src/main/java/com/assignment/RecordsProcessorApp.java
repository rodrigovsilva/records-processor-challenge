package com.assignment;

import com.assignment.records.ProcessRecords;
import com.assignment.records.ProcessRecordsFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * RecordsProcessorApp is the main class for the Records Processor application.
 */
public class RecordsProcessorApp {

    private static final Logger logger = LoggerFactory.getLogger(RecordsProcessorApp.class);

    private final ProcessRecords processRecords; // Declare processRecords as a final instance variable

    public RecordsProcessorApp(ProcessRecords processRecords) {
        this.processRecords = processRecords;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.warn("Usage: java -jar records-processor.jar <input-file>");
            System.exit(1);
        }

        Path inputFile = Path.of(args[0]);
        ProcessRecords processRecords = new ProcessRecordsFromFile();
        RecordsProcessorApp app = new RecordsProcessorApp(processRecords);

        app.processFile(inputFile);
    }

    public void processFile(Path inputFile) {
        try {
            processRecords.from(inputFile);
        } catch (Exception e) {
            logger.error("Failed to process file. Original Message: {}", e.getMessage(), e);
        }
    }
}
