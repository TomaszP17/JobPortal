package com.jobportal.jobportal.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
