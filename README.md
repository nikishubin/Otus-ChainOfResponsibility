# Otus-ChainOfResponsibility

###Project Modules:
- application
- usecase
- file-writer

---

Flow:
1. Write result to a file passed to arg[0];
2. Read left border of fibonacci sequence from arg[1];
3. Read right border of fibonacci sequence from arg[2].

Supported fibonacci sequence calculation algorithms:
1. Binet;
2. Iterative;

If sequence is descending, Binet algorithm used for calculating fibonacci numbers.
If sequence is ascending, Iterative algorithm used for calculating fibonacci numbers.

Command example:

`gradle clean application:run --args="D:\\Projects\\Otus\\iterator_output.txt 5 0"`

---

Used technologies:
- Java 14 modular without experimental functionality
- Gradle 6.5
- jUnit 5 (Jupiter)