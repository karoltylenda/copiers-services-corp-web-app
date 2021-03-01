package com.tytanisukcesu.copiers.service.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super(username + " not found.");

    }
}
