package ru.otus.patterns.external.writer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.external.writer.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public final class OutputStreamWriter implements FileWriter {
    private final Logger log = LogManager.getLogger(OutputStreamWriter.class);
    private final String path;

    public OutputStreamWriter(String path) {
        this.path = path;
    }

    @Override
    public void write(byte[] target) throws IOException {
        File source = Paths.get(path).toFile();
        try (FileOutputStream out = new FileOutputStream(source)) {
            out.write(target);
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("File with path {} not found!", path);
            throw fileNotFoundException;
        } catch (IOException ioException) {
            log.error("File with path {} is broken!", path);
            throw ioException;
        }
    }
}
