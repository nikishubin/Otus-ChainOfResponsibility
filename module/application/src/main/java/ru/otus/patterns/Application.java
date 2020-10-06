package ru.otus.patterns;

import ru.otus.patterns.adapter.input.FileToData;
import ru.otus.patterns.adapter.output.DataToFile;
import ru.otus.patterns.external.reader.impl.FileStreamReader;
import ru.otus.patterns.external.writer.impl.OutputStreamWriter;
import ru.otus.patterns.responsibility.DataHandler;
import ru.otus.patterns.responsibility.concrete.CsvHandler;
import ru.otus.patterns.responsibility.concrete.JsonHandler;
import ru.otus.patterns.responsibility.concrete.TxtHandler;
import ru.otus.patterns.responsibility.concrete.XmlHandler;
import ru.otus.patterns.responsibility.port.DataInput;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        String sourcePath = args[0];
        String outputPath = args[1];

        DataInput input = new FileToData(new FileStreamReader(), sourcePath);
        DataOutput output = new DataToFile(new OutputStreamWriter(outputPath));
        DataHandler first = constructHandlerChain();

        List<String> sources = input.read();
        for (String source : sources) {
            DataInput localInput = new FileToData(new FileStreamReader(), source);
            first.handle(localInput, output);
        }
    }

    private static DataHandler constructHandlerChain() {
        final DataHandler txtHandler = new TxtHandler();
        final DataHandler csvHandler = new CsvHandler();
        final DataHandler jsonHandler = new JsonHandler();
        final DataHandler xmlHandler = new XmlHandler();

        txtHandler.setNext(csvHandler);
        csvHandler.setNext(jsonHandler);
        jsonHandler.setNext(xmlHandler);

        return txtHandler;
    }
}
