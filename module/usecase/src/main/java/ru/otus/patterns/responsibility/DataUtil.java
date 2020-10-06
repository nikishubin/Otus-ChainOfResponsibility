package ru.otus.patterns.responsibility;

import java.util.Optional;

public final class DataUtil {

    private DataUtil() {
    }

    public static String getFileExtension(String pathToFile) {
        return Optional.ofNullable(pathToFile)
                .filter(path -> path.contains("."))
                .map(path -> path.substring(pathToFile.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Selected file (%s) don't have extension!", pathToFile)));
    }
}
