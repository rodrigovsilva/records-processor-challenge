package com.assignment.records;

import com.assignment.records.model.TransactionRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GenerateReportWithInvalidTransactionsTest {

    private static final Logger logger = LoggerFactory.getLogger(GenerateReportWithInvalidTransactionsTest.class);

    private GenerateReportWithInvalidTransactions reportGenerator;
    private Path tempDir;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        reportGenerator = new GenerateReportWithInvalidTransactions();
        tempDir = Files.createTempDirectory("test");
        tempFile = tempDir.resolve("test-report.csv");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up: Delete the temporary directory and its contents
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempDir);
    }

    @Test
    public void testGenerateReport_FileCreatedSuccessfully() throws IOException {
        // Create some transaction records for testing
        List<TransactionRecord> transactionRecords = Arrays.asList(
                new TransactionRecord("1", "NL91RABO0315273637", "Description 1", new BigDecimal("50"), new BigDecimal("-10"), new BigDecimal("10")),
                new TransactionRecord("2", "NL27SNSB0917829871", "Description 2", new BigDecimal("100"), new BigDecimal("2.50"), new BigDecimal("93.50"))
        );

        // Generate the report
        reportGenerator.from(tempFile, transactionRecords);

        // Assert that the file was created
        assertTrue(Files.exists(tempFile), "Report file should exist");

        // Verify the content of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.toFile()))) {
            // Check the header
            String header = reader.readLine();
            assertEquals("Reference,Description", header.trim(), "Header mismatch");

            // Check each transaction record line
            List<String> expectedLines = Arrays.asList(
                    "1,Description 1",
                    "2,Description 2"
            );

            for (String expectedLine : expectedLines) {
                String actualLine = reader.readLine().trim();
                assertEquals(expectedLine, actualLine, "Line content mismatch");
            }

            assertNull(reader.readLine(), "File contains extra lines");
        } catch (IOException e) {
            fail("Failed to read report file: " + e.getMessage());
        }
    }
}
