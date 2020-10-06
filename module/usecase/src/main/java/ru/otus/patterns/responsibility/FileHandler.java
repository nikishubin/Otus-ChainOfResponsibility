package ru.otus.patterns.responsibility;

public abstract class FileHandler {

    protected FileHandler next;

    public abstract boolean handle(FileType type, String pathToFile);

    protected boolean pass(FileType type, String pathToFile) {
        return next.handle(type, pathToFile);
    }

    public void setNext(FileHandler next) {
        this.next = next;
    }
}
