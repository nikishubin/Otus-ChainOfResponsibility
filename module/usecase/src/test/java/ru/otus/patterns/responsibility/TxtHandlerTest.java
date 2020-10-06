package ru.otus.patterns.responsibility;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.patterns.responsibility.concrete.JsonHandler;
import ru.otus.patterns.responsibility.concrete.TxtHandler;
import ru.otus.patterns.responsibility.port.DataInput;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;

@ExtendWith({MockitoExtension.class})
class TxtHandlerTest {

    @Mock
    private DataInput input;

    @Mock
    private DataOutput output;

    @Spy
    private TxtHandler handler;

    @Test
    void whenHandleAvailableTypeThenProcess() throws IOException {
        Mockito.when(input.getSource()).thenReturn("test.txt");

        boolean isHandled = handler.handle(input, output);
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }

    @Test
    void whenHandleUnavailableTypeThenPass() throws IOException {
        Mockito.when(input.getSource()).thenReturn("test.json");

        handler.setNext(new JsonHandler());
        boolean isHandled = handler.handle(input, output);

        Mockito.verify(handler, Mockito.times(1)).pass(input, output);
        Assertions.assertThat(isHandled).isEqualTo(Boolean.TRUE);
    }
}
