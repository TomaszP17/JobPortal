package com.jobportal.jobportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkTypeDoesNotExistsException extends RuntimeException{
    public WorkTypeDoesNotExistsException(String message) {
        super(message);
    }
}
