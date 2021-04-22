package com.tytanisukcesu.copiers.service.exception;

public class CounterIncorrectValuesException extends RuntimeException {

    public CounterIncorrectValuesException(){
        super("Incorrect counter values");
    }
}
