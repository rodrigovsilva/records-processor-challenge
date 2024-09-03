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

public class ReadRecordsFromJsonTest {

    @Test
    public void testFrom_GivenValidJsonData_WhenJsonRecordsAreProperlyParsed_ThenReturnListOfTransactionRecords() throws Exception {
        // Create a temporary Json file
        Path tempFile = Files.createTempFile("test-input", ".Json");

        // Write the Json content to the temporary file
        String JsonContent = """
                [
                  {
                    "reference": "130498",
                    "accountNumber": "NL69ABNA0433647324",
                    "description": "Book Jan Theuß",
                    "startBalance": 26.9,
                    "mutation": -18.78,
                    "endBalance": 8.12
                  },
                  {
                    "reference": "167875",
                    "accountNumber": "NL93ABNA0585619023",
                    "description": "Toy Greg Alysha",
                    "startBalance": 5429,
                    "mutation": -939,
                    "endBalance": 6368
                  }
                ]
                """;
        Files.writeString(tempFile, JsonContent, StandardOpenOption.WRITE);

        // Test the method
        List<TransactionRecord> result = new ReadRecordsFromJson().from(tempFile);

        tempFile.toFile().deleteOnExit();

        // Assertions
        var expectedRecordCount = 2;
        assertEquals(expectedRecordCount, result.size(), "Number of records read should be %d".formatted(expectedRecordCount));

        TransactionRecord transactionRecord1 = result.get(0);
        assertEquals("130498", transactionRecord1.reference());
        assertEquals("NL69ABNA0433647324", transactionRecord1.accountNumber());
        assertEquals("Book Jan Theuß", transactionRecord1.description());
        assertEquals(new BigDecimal("26.9"), transactionRecord1.startBalance());
        assertEquals(new BigDecimal("-18.78"), transactionRecord1.mutation());
        assertEquals(new BigDecimal("8.12"), transactionRecord1.endBalance());

        TransactionRecord transactionRecord2 = result.get(1);
        assertEquals("167875", transactionRecord2.reference());
        assertEquals("NL93ABNA0585619023", transactionRecord2.accountNumber());
        assertEquals("Toy Greg Alysha", transactionRecord2.description());
        assertEquals(new BigDecimal("5429"), transactionRecord2.startBalance());
        assertEquals(new BigDecimal("-939"), transactionRecord2.mutation());
        assertEquals(new BigDecimal("6368"), transactionRecord2.endBalance());
    }

    @Test
    public void testFrom_GivenInvalidJsonData_WhenJsonRecordsAreNotParsed_ThenThrowException() throws Exception {
        // Create a temporary Json file
        Path tempFile = Files.createTempFile("test-input", ".Json");

        // Write the Json content to the temporary file
        String JsonContent = """
                {"xxx":1234}
                """;

        Files.writeString(tempFile, JsonContent, StandardOpenOption.WRITE);

        // Test the method
        var exception = Assertions.assertThrowsExactly(CanNotProcessFileException.class, () -> new ReadRecordsFromJson().from(tempFile));

        tempFile.toFile().deleteOnExit();

        // Assertions
        assertEquals("Failed parsing file: %s".formatted(tempFile.toString()), exception.getMessage());


    }
}
