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
class TxtHandlerTest {

    @Spy
    private TxtHandler handler;

    @Test
    void whenHandleAvailableTypeThenProcess() {
        boolean isHandled = handler.handle(FileType.TXT, "test.txt");
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }

    @Test
    void whenHandleUnavailableTypeThenPass() {
        String pathToFile = "test.csv";
        FileType type = FileType.CSV;

        handler.setNext(new CsvHandler());
        boolean isHandled = handler.handle(type, pathToFile);

        Mockito.verify(handler, Mockito.times(1)).pass(type, pathToFile);
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }
}
