package ru.otus.patterns.responsibility.concrete;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.responsibility.DataHandler;
import ru.otus.patterns.responsibility.DataUtil;
import ru.otus.patterns.responsibility.port.DataInput;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;
import java.util.List;

public class XmlHandler extends DataHandler {
    private static final Logger log = LogManager.getLogger(XmlHandler.class);

    @Override
    public boolean handle(DataInput input, DataOutput output) throws IOException {
        if (next != null && !"xml".equals(DataUtil.getFileExtension(input.getSource()))) {
            log.info("{} pass {} to {}!", this.getClass().getSimpleName(), input.getSource(), next.getClass().getSimpleName());
            return pass(input, output);
        }

        log.info("{} handle {}!", this.getClass().getSimpleName(), input.getSource());
        List<String> data = input.read();
        for (String row : data) {
            output.write(row);
        }
        return true;
    }
}
