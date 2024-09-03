package com.assignment.records;

import com.assignment.records.exception.CanNotDefineFileTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FileTypeTest {

    static Stream<Arguments> testFrom_GivenKnownFileType_WhenFileTypeIsCorrect_ThenReturnCorrectFileType() {
        return Stream.of(
                Arguments.of("file.csv", FileType.CSV, false),
                Arguments.of("file.json", FileType.JSON, false),
                Arguments.of("file.txt", null, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFrom_GivenKnownFileType_WhenFileTypeIsCorrect_ThenReturnCorrectFileType(String fileType, FileType expectedFileType, boolean expectedError) {
        if (expectedError) {
            Assertions.assertThrows(CanNotDefineFileTypeException.class, () -> FileType.from(fileType));
        } else {
            Assertions.assertEquals(expectedFileType, FileType.from(fileType));
        }
    }
}
