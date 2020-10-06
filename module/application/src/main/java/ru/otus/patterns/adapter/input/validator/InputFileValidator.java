package ru.otus.patterns.adapter.input.validator;


import ru.otus.patterns.adapter.input.exeption.DelimiterIsNotValidException;
import ru.otus.patterns.adapter.input.exeption.FileIsEmptyException;

public class InputFileValidator {

    private InputFileValidator() {
    }

    public static void checkFileIsEmpty(String source) {
        if (source.isBlank()) {
            throw new FileIsEmptyException("Source file is empty!");
        }
    }

    public static void checkDelimiterIsCorrect(String source, String delimiter) {
        String[] elements = source.split(delimiter);
        if (elements.length < 1) {
            throw new DelimiterIsNotValidException(String.format("Passed delimiter %s is not valid!", delimiter));
        }
    }
}
