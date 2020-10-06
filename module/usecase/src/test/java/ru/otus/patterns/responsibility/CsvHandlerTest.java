package ru.otus.patterns.responsibility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.patterns.responsibility.concrete.CsvHandler;
import ru.otus.patterns.responsibility.concrete.TxtHandler;

@ExtendWith({MockitoExtension.class})
class CsvHandlerTest {

    @Spy
    private CsvHandler handler;

    @Test
    void whenHandleAvailableTypeThenProcess() {
        boolean isHandled = handler.handle(FileType.CSV, "test.csv");
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }

    @Test
    void whenHandleUnavailableTypeThenPass() {
        String pathToFile = "test.txt";
        FileType type = FileType.TXT;

        handler.setNext(new TxtHandler());
        boolean isHandled = handler.handle(type, pathToFile);

        Mockito.verify(handler, Mockito.times(1)).pass(type, pathToFile);
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }
}
