package ru.otus.patterns.responsibility.concrete;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.patterns.responsibility.FileHandler;
import ru.otus.patterns.responsibility.FileType;

public class TxtHandler extends FileHandler {
    private static final Logger log = LogManager.getLogger(TxtHandler.class);

    @Override
    public boolean handle(FileType type, String pathToFile) {
        if (next != null && !FileType.TXT.equals(type)) {
            log.info("{} pass {} to {}!", this.getClass().getSimpleName(), pathToFile, next.getClass().getSimpleName());
            return pass(type, pathToFile);
        }

        log.info("{} handle {}!", this.getClass().getSimpleName(), pathToFile);
        return true;
    }
}
