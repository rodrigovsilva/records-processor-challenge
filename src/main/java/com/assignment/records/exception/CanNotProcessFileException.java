package com.assignment.records.exception;

/**
 * CanNotProcessFileException is a custom exception class for files that cannot be processed.
 */
public class CanNotProcessFileException extends RuntimeException {

    private static final String FAILED_PARSING_FILE_ERROR_MESSAGE = "Failed parsing file: %s";

    private CanNotProcessFileException(String message) {
        super(message);
    }

    private CanNotProcessFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CanNotProcessFileException failedParsingFile(String fileName, Throwable cause) {
        return new CanNotProcessFileException(FAILED_PARSING_FILE_ERROR_MESSAGE.formatted(fileName), cause);
    }
}
