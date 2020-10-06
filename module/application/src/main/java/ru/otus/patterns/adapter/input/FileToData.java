package ru.otus.patterns.adapter.input;

import ru.otus.patterns.adapter.input.validator.InputFileValidator;
import ru.otus.patterns.external.reader.FileReader;
import ru.otus.patterns.responsibility.port.DataInput;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileToData implements DataInput {
    private static final String DEFAULT_ELEMENT_DELIMITER = "(\\n)";

    private final FileReader reader;
    private final String pathToFile;
    private final String elementDelimiter;

    public FileToData(FileReader reader, String pathToFile) {
        this.reader = reader;
        this.pathToFile = pathToFile;
        this.elementDelimiter = DEFAULT_ELEMENT_DELIMITER;
    }

    public FileToData(FileReader reader, String pathToFile, String elementDelimiter) {
        this.reader = reader;
        this.pathToFile = pathToFile;
        this.elementDelimiter = elementDelimiter;
    }

    @Override
    public List<String> read() throws IOException {
        String source = getSourceFromFile();
        InputFileValidator.checkDelimiterIsCorrect(source, elementDelimiter);

        return Arrays.stream(source.split(elementDelimiter)).map(String::strip).collect(Collectors.toList());
    }

    private String getSourceFromFile() throws IOException {
        String source = new String(reader.read(pathToFile), Charset.defaultCharset());
        InputFileValidator.checkFileIsEmpty(source);
        return source;
    }
}
