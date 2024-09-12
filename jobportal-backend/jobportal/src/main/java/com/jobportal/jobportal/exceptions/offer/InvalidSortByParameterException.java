package com.jobportal.jobportal.exceptions.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSortByParameterException extends RuntimeException{
    public InvalidSortByParameterException(String message) {
        super(message);
    }
}
