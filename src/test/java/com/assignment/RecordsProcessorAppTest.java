package com.assignment;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RecordsProcessorAppTest {

    @Test
    public void testMain_GivenInvalidArguments_WhenWrongAmountOfArguments_ThenReturnsException() {
        new MockUp<System>() {
            @Mock
            public void exit(int value) {
                throw new RuntimeException(String.valueOf(value));
            }
        };

        String[] args = new String[0];

        var runtimeException = Assertions.assertThrows(RuntimeException.class, () -> RecordsProcessorApp.main(args));

        Assertions.assertEquals("1", runtimeException.getMessage());
    }

    @Test
    public void testMain_GivenArguments_WhenExpectedArgumentsAreProvided_ThenProcessFile() throws IOException {
        // Create a temporary CSV file
        Path tempFile = Files.createTempFile("test-input", ".csv");

        // Write the CSV content to the temporary file
        String csvContent = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n" +
                "194261,NL91RABO0315273637,Book John Smith,21.6,-41.83,-20.23\n";
        Files.writeString(tempFile, csvContent, StandardOpenOption.WRITE);

        // Ensure temp file is deleted after test
        tempFile.toFile().deleteOnExit();

        // Call the main method with the path of the temp file
        String[] args = {tempFile.toString()};

        // Capture output or exception if expected
        Assertions.assertDoesNotThrow(() -> RecordsProcessorApp.main(args));

        // Clean up - explicitly delete temp file if not using deleteOnExit (optional)
        Files.deleteIfExists(tempFile);
    }
}
