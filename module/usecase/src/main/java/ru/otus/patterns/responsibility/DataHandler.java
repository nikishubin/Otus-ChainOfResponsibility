package ru.otus.patterns.responsibility;

import ru.otus.patterns.responsibility.port.DataInput;
import ru.otus.patterns.responsibility.port.DataOutput;

import java.io.IOException;

public abstract class DataHandler {

    protected DataHandler next;

    public abstract boolean handle(DataInput input, DataOutput output) throws IOException;

    protected boolean pass(DataInput input, DataOutput output) throws IOException {
        return next.handle(input, output);
    }

    public void setNext(DataHandler next) {
        this.next = next;
    }
}
