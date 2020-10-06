package ru.otus.patterns.responsibility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.patterns.responsibility.concrete.CsvHandler;
import ru.otus.patterns.responsibility.concrete.XmlHandler;

@ExtendWith({MockitoExtension.class})
class XmlHandlerTest {

    @Spy
    private XmlHandler handler;

    @Test
    void whenHandleAvailableTypeThenProcess() {
        boolean isHandled = handler.handle(FileType.XML, "test.xml");
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
