package com.jobportal.jobportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TechnologyDoesNotExistsException extends RuntimeException {
    public TechnologyDoesNotExistsException(String message) {
        super(message);
    }
}
