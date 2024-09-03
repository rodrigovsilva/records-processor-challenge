package com.assignment.records.exception;

/**
 * CanNotCreateReportFileException is a custom exception that is thrown when a report file cannot be created.
 */
public class CanNotCreateReportFileException extends RuntimeException {

    private static final String FAILED_GENERATING_FILE_ERROR_MESSAGE = "Failed generating file report: %s";

    private CanNotCreateReportFileException(String message) {
        super(message);
    }

    private CanNotCreateReportFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CanNotCreateReportFileException failedGeneratingFile(String fileName, Throwable cause) {
        return new CanNotCreateReportFileException(FAILED_GENERATING_FILE_ERROR_MESSAGE.formatted(fileName), cause);
    }
}
