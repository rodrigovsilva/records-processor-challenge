package com.assignment.records;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.assignment.records.logger.MemoryAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessRecordsFromFileTest {

    private MemoryAppender memoryAppender;
    private ProcessRecordsFromFile processRecordsFromFile;

    @BeforeEach
    public void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(ProcessRecordsFromFile.class);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        // Initialize the class under test
        processRecordsFromFile = new ProcessRecordsFromFile();
    }

    @Test
    public void testFrom_GivenValidTransactions_WhenProcessed_ThenNoInvalidRecords() throws IOException {
        // Create a temporary CSV file
        Path tempFile = Files.createTempFile("test-input", ".csv");

        // Write the CSV content to the temporary file
        String csvContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n" +
                "194261,NL91RABO0315273637,Book John Smith,20,-10,10\n" +
                "112806,NL27SNSB0917829871,Clothes Irma Steven,91,+2.50,93.50\n";
        Files.writeString(tempFile, csvContent, StandardOpenOption.WRITE);

        // Test the method
        processRecordsFromFile.from(tempFile);

        tempFile.toFile().deleteOnExit();

        // Verify logs
        assertThat(memoryAppender.countEventsForLogger(ProcessRecordsFromFile.class)).isEqualTo(2);
        assertThat(memoryAppender.search("No invalid records found").size()).isEqualTo(1);
    }

    @Test
    public void testFrom_GivenInvalidTransactions_WhenProcessed_ThenLogInvalidRecords() throws IOException {
        // Create a temporary CSV file
        Path tempFile = Files.createTempFile("test-input", ".csv");

        // Write the CSV content to the temporary file
        String csvContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n" +
                "194261,NL91RABO0315273637,Book John Smith,20,-10,100\n" +
                "112806,NL27SNSB0917829871,Clothes Irma Steven,91,+23.50,93.50\n";
        Files.writeString(tempFile, csvContent, StandardOpenOption.WRITE);

        // Test the method
        processRecordsFromFile.from(tempFile);

        tempFile.toFile().deleteOnExit();

        // Verify logs
        assertThat(memoryAppender.countEventsForLogger(ProcessRecordsFromFile.class)).isEqualTo(2);
        assertThat(memoryAppender.search("Report generated at:").size()).isEqualTo(1);
    }
}


// /var/folders/4h/89mdw92j2674_5l0vgs62w4c0000gp/T/test-input13242469369014563910-report.csv