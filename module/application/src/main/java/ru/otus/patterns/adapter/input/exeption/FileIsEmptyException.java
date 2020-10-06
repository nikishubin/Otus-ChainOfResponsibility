package ru.otus.patterns.adapter.input.exeption;

public final class FileIsEmptyException extends RuntimeException {

    public FileIsEmptyException(String message) {
        super(message);
    }
}
