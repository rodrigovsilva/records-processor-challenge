package com.assignment.records;

import com.assignment.records.exception.CanNotProcessFileException;
import com.assignment.records.model.TransactionRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadRecordsFromCsvTest {

    @Test
    public void testFrom_GivenValidCsvData_WhenCsvRecordsAreProperlyParsed_ThenReturnListOfTransactionRecords() throws Exception {
        // Create a temporary CSV file
        Path tempFile = Files.createTempFile("test-input", ".csv");

        // Write the CSV content to the temporary file
        String csvContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n" +
                "194261,NL91RABO0315273637,Book John Smith,21.6,-41.83,-20.23\n" +
                "112806,NL27SNSB0917829871,Clothes Irma Steven,91.23,+15.57,106.8\n";
        Files.writeString(tempFile, csvContent, StandardOpenOption.WRITE);

        // Test the method
        List<TransactionRecord> result = new ReadRecordsFromCsv().from(tempFile);

        tempFile.toFile().deleteOnExit();

        // Assertions
        var expectedRecordCount = 2;
        assertEquals(expectedRecordCount, result.size(), "Number of records read should be %d".formatted(expectedRecordCount));

        TransactionRecord transactionRecord1 = result.get(0);
        assertEquals("194261", transactionRecord1.reference());
        assertEquals("NL91RABO0315273637", transactionRecord1.accountNumber());
        assertEquals("Book John Smith", transactionRecord1.description());
        assertEquals(new BigDecimal("21.6"), transactionRecord1.startBalance());
        assertEquals(new BigDecimal("-41.83"), transactionRecord1.mutation());
        assertEquals(new BigDecimal("-20.23"), transactionRecord1.endBalance());

        TransactionRecord transactionRecord2 = result.get(1);
        assertEquals("112806", transactionRecord2.reference());
        assertEquals("NL27SNSB0917829871", transactionRecord2.accountNumber());
        assertEquals("Clothes Irma Steven", transactionRecord2.description());
        assertEquals(new BigDecimal("91.23"), transactionRecord2.startBalance());
        assertEquals(new BigDecimal("15.57"), transactionRecord2.mutation());
        assertEquals(new BigDecimal("106.8"), transactionRecord2.endBalance());
    }

    @Test
    public void testFrom_GivenInvalidCsvData_WhenCsvRecordsAreNotParsed_ThenThrowException() throws Exception {
        // Create a temporary CSV file
        Path tempFile = Files.createTempFile("test-input", ".csv");

        // Write the CSV content to the temporary file
        String csvContent = "Reference|AccountNumber|Description,Start Balance,Mutation,End Balance\n" +
                "194261,NL91RABO0315273637|Book John Smith,21.6,-41.83,-20.23\n" +
                "112806,NL27SNSB0917829871''''Clothes Irma Steven,91.23,+15.57,106.8\n";
        Files.writeString(tempFile, csvContent, StandardOpenOption.WRITE);

        // Test the method
        var exception = Assertions.assertThrowsExactly(CanNotProcessFileException.class, () -> new ReadRecordsFromCsv().from(tempFile));

        tempFile.toFile().deleteOnExit();

        // Assertions
        assertEquals("Failed parsing file: %s".formatted(tempFile.toString()), exception.getMessage());
    }
}
