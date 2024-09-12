package com.jobportal.jobportal.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FavouriteOfferAlreadyExistsException extends RuntimeException{
    public FavouriteOfferAlreadyExistsException(String message) {
        super(message);
    }
}
