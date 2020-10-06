package ru.otus.patterns.adapter.output;

import ru.otus.patterns.external.writer.FileWriter;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;

public class DataToFile implements DataOutput {

    private final FileWriter writer;

    public DataToFile(FileWriter writer) {
        this.writer = writer;
    }

    @Override
    public void write(String target) throws IOException {
        writer.write(target.getBytes());
    }
}
