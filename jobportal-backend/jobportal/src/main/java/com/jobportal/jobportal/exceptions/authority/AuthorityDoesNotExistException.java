package com.jobportal.jobportal.exceptions.authority;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorityDoesNotExistException extends RuntimeException{
    public AuthorityDoesNotExistException(String message) {
        super(message);
    }
}
