package com.telebeer.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeerExistsInOtherListException extends IllegalArgumentException {
    public BeerExistsInOtherListException() {
        super("You have added this beer in your other list with beers." +
                "You must unmark it from there if you want to add it in current list.");
    }
}
