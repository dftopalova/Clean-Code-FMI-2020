package com.telebeer.beertag.exceptions;

public class MalformedRequestException extends IllegalArgumentException {

    public MalformedRequestException(String message) {
        super(message);
    }

}
