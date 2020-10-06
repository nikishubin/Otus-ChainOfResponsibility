# Otus-ChainOfResponsibility

###Project Modules:
- application
- usecase
- file-writer
- file-reader

---

Flow:
1. Read source paths from a file passed to arg[0];
2. Iterate through chain of responsibility to handler;
3. Copy data from a source file to a file passed to arg[1].

Supported file types:
1. .txt;
2. .json;
3. .csv;
4. .xml

Command example:

`gradle clean application:run --args="D:\\Projects\\Otus\\chain_input.txt D:\\Projects\\Otus\\chain_output.txt"`

---

Used technologies:
- Java 14 modular without experimental functionality
- Gradle 6.5
- jUnit 5 (Jupiter)