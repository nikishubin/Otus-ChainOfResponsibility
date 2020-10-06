package ru.otus.patterns.external.writer;

import java.io.IOException;

public interface FileWriter {

    void write(byte[] target) throws IOException;
}
