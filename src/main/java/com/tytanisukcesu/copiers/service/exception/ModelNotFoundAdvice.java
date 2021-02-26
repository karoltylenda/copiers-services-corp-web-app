package com.tytanisukcesu.copiers.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ModelNotFoundAdvice {

    //jak tylko zostanie wywolana klasa ModelNotFoundException to od razu ten element zostanie przechwycony
    //TO JEST TZW. AOP - aspekt

    @ResponseBody //przemapowanie do jsona
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String modelNotFoundHandler(ModelNotFoundException modelNotFoundException){
        return modelNotFoundException.getMessage();
    }



}
