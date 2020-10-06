package ru.otus.patterns.responsibility.port;

import java.io.IOException;
import java.util.List;

public interface DataInput {

    List<String> read() throws IOException;
}
