package ru.otus.patterns.responsibility.port;

import java.io.IOException;
import java.util.List;

public interface DataInput {

    String getSource();

    List<String> read() throws IOException;
}
