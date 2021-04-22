package com.tytanisukcesu.copiers.service.exception;

public class DeviceNotFoundException extends RuntimeException {

    public DeviceNotFoundException(Long id){
        super("Could not find device for id: "+id);
    }
}
