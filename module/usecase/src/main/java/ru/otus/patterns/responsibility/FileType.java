package ru.otus.patterns.responsibility;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FileType {
    TXT,
    CSV,
    JSON,
    XML;

    private static final Map<String, FileType> types;

    static {
        types = Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(
                next -> next.name().toLowerCase(), Function.identity()
        ));
    }

    public static FileType getType(String name) {
        return Optional.ofNullable(types.get(name.toLowerCase()))
                .orElseThrow(() -> new NoSuchElementException("Selected type is not supported!"));
    }
}
