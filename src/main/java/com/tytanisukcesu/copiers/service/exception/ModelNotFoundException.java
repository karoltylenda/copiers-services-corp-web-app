package com.tytanisukcesu.copiers.service.exception;

public class ModelNotFoundException extends RuntimeException{

    //ten blad jest przechwytywany przez wątek AOP

    public ModelNotFoundException(Long id,String modelName) {
        super("Could not find " + modelName + " for id: "+ id);
    }


}
