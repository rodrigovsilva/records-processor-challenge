package com.assignment.records;

import com.assignment.records.exception.CanNotDefineFileTypeException;

/**
 * FileType is an enum class for file types.
 */
public enum FileType {
    CSV(".csv"),
    JSON(".json");

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    /**
     * This method returns the FileType based on the file extension.
     *
     * @param fileName the name of the file
     * @return the FileType based on the file extension
     */
    public static FileType from(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();
        for (FileType fileType : values()) {
            if (lowerCaseFileName.endsWith(fileType.extension())) {
                return fileType;
            }
        }
        throw CanNotDefineFileTypeException.unsupportedFileType(fileName);
    }

    public String extension() {
        return extension;
    }
}
