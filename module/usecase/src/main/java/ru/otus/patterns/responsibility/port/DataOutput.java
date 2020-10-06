package ru.otus.patterns.responsibility.port;

import java.io.IOException;

public interface DataOutput {

    void write(String target) throws IOException;
}
