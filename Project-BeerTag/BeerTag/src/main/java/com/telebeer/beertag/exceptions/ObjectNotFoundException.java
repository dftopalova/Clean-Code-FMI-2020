package com.telebeer.beertag.exceptions;

public class ObjectNotFoundException extends IllegalArgumentException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
