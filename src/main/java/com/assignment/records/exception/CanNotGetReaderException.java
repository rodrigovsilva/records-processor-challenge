package com.assignment.records.exception;

/**
 * CanNotGetReaderException is a custom exception class for unknown file readers.
 */
public class CanNotGetReaderException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Cannot get reader for file: %s";

    private CanNotGetReaderException(String message) {
        super(message);
    }

    private CanNotGetReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CanNotGetReaderException unknownFileReader(String fileName) {
        return new CanNotGetReaderException(ERROR_MESSAGE.formatted(fileName));
    }
}