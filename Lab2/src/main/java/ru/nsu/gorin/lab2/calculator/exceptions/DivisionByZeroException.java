package ru.nsu.gorin.lab2.calculator.exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
