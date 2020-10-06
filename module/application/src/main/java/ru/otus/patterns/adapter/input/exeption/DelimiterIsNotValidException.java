package ru.otus.patterns.adapter.input.exeption;

public final class DelimiterIsNotValidException extends RuntimeException {

    public DelimiterIsNotValidException(String message) {
        super(message);
    }
}
