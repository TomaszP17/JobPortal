package com.jobportal.jobportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferDoesNotExistsException extends RuntimeException{

    private String message;

    public OfferDoesNotExistsException(String message) {
        super(message);
        this.message = message;
    }
}
