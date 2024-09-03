package com.assignment.records.exception;

/**
 * CanNotDefineFileTypeException is a custom exception class for unsupported file types.
 */
public class CanNotDefineFileTypeException extends RuntimeException {

    public static final String UNSUPPORTED_FILE_TYPE_ERROR_MESSAGE = "Unsupported file type: %s";

    private CanNotDefineFileTypeException(String message) {
        super(message);
    }

    private CanNotDefineFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CanNotDefineFileTypeException unsupportedFileType(String fileName) {
        return new CanNotDefineFileTypeException(UNSUPPORTED_FILE_TYPE_ERROR_MESSAGE.formatted(fileName));
    }
}
