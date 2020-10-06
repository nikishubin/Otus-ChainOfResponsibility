package ru.otus.patterns.external.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.external.reader.FileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public final class FileStreamReader implements FileReader {
    private final Logger log = LogManager.getLogger(FileStreamReader.class);

    @Override
    public byte[] read(String pathToSource) throws IOException {
        File source = Paths.get(pathToSource).toFile();
        try (FileInputStream inputStream = new FileInputStream(source)) {
            return inputStream.readAllBytes();
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("File with path {} not found!", pathToSource);
            throw fileNotFoundException;
        } catch (IOException ioException) {
            log.error("File with path {} is broken!", pathToSource);
            throw ioException;
        }
    }
}
