package com.assignment.records;

import com.assignment.records.exception.CanNotProcessFileException;
import com.assignment.records.model.TransactionRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * ReadRecordsFromJson is a class for reading records data from JSON files.
 */
public class ReadRecordsFromJson implements ReadRecords {
    @Override
    public List<TransactionRecord> from(Path inputFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonContent = Files.readString(inputFile);

            return mapper.readValue(jsonContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw CanNotProcessFileException.failedParsingFile(inputFile.toString(), e);
        }
    }
}