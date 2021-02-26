package com.tytanisukcesu.copiers.service.exception;

public class ModelNotFoundException extends RuntimeException{

    //ten blad jest przechwytywany przez wątek AOP

    public ModelNotFoundException(Long id) {
        super("Could not find for id: "+ id);
    }
}
