package com.jobportal.jobportal.exceptions.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmploymentTypeDoesNotExistsException extends RuntimeException{
    public EmploymentTypeDoesNotExistsException(String message) {
        super(message);
    }
}
