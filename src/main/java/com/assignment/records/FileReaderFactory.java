package com.assignment.records;

import com.assignment.records.exception.CanNotGetReaderException;

import java.nio.file.Path;

/**
 * FileReaderFactory is a factory class for creating file processors.
 */
public class FileReaderFactory {

    /**
     * Returns a file processor based on the file type.
     *
     * @param inputFile the input file to process
     * @return a file processor
     */
    public static ReadRecords getReader(Path inputFile) {
        String fileName = inputFile.toString().toLowerCase();

        try {
            return switch (FileType.from(fileName)) {
                case CSV -> new ReadRecordsFromCsv();
                case JSON -> new ReadRecordsFromJson();
            };
        } catch (RuntimeException e) {
            throw CanNotGetReaderException.unknownFileReader(fileName);
        }
    }
}