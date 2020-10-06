package ru.otus.patterns;

import ru.otus.patterns.adapter.input.FileToData;
import ru.otus.patterns.adapter.output.DataToFile;
import ru.otus.patterns.external.reader.impl.FileStreamReader;
import ru.otus.patterns.external.writer.impl.OutputStreamWriter;
import ru.otus.patterns.responsibility.FileHandler;
import ru.otus.patterns.responsibility.FileType;
import ru.otus.patterns.responsibility.concrete.CsvHandler;
import ru.otus.patterns.responsibility.concrete.JsonHandler;
import ru.otus.patterns.responsibility.concrete.TxtHandler;
import ru.otus.patterns.responsibility.concrete.XmlHandler;
import ru.otus.patterns.responsibility.port.DataInput;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Application {

    public static void main(String[] args) throws IOException {
        String sourcePath = args[0];
        String outputPath = args[1];

        DataInput input = new FileToData(new FileStreamReader(), sourcePath);
        DataOutput output = new DataToFile(new OutputStreamWriter(outputPath));
        FileHandler first = constructHandlerChain();

        List<String> sources = input.read();
        for (String source : sources) {
            first.handle(FileType.getType(getFileExtension(source)), source);
        }
    }

    private static FileHandler constructHandlerChain() {
        final FileHandler txtHandler = new TxtHandler();
        final FileHandler csvHandler = new CsvHandler();
        final FileHandler jsonHandler = new JsonHandler();
        final FileHandler xmlHandler = new XmlHandler();

        txtHandler.setNext(csvHandler);
        csvHandler.setNext(jsonHandler);
        jsonHandler.setNext(xmlHandler);

        return txtHandler;
    }

    private static String getFileExtension(String pathToFile) {
        return Optional.ofNullable(pathToFile)
                .filter(path -> path.contains("."))
                .map(path -> path.substring(pathToFile.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Selected file (%s) don't have extension!", pathToFile)));
    }
}
