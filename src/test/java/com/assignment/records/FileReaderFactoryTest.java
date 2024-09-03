package com.assignment.records;

import com.assignment.records.exception.CanNotGetReaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FileReaderFactoryTest {

    @Test
    public void testGetReaderCsv() {
        // Mock the Path object
        Path mockPath = Mockito.mock(Path.class);
        Mockito.when(mockPath.toString()).thenReturn("test.csv");

        // Call getProcessor to get the processor for CSV file
        ReadRecords processor = FileReaderFactory.getReader(mockPath);

        // Verify that a ReadRecordsFromCsv instance is returned
        assertInstanceOf(ReadRecordsFromCsv.class, processor);
    }

    @Test
    public void testGetReaderJson() {
        // Mock the Path object
        Path mockPath = Mockito.mock(Path.class);
        Mockito.when(mockPath.toString()).thenReturn("test.json");

        // Call getProcessor to get the processor for JSON file
        ReadRecords processor = FileReaderFactory.getReader(mockPath);

        // Verify that a ReadRecordsFromJson instance is returned
        assertInstanceOf(ReadRecordsFromJson.class, processor);
    }

    @Test
    public void testGetReaderUnknownFileType() {
        // Mock the Path object
        Path mockPath = Mockito.mock(Path.class);
        Mockito.when(mockPath.toString()).thenReturn("test.txt");

        // Call getProcessor to get the processor for an unknown file type
        var exception = Assertions.assertThrowsExactly(CanNotGetReaderException.class, () -> FileReaderFactory.getReader(mockPath));

        // Verify that null is returned for unknown file types
        assertEquals("Cannot get reader for file: test.txt", exception.getMessage());
    }
}
