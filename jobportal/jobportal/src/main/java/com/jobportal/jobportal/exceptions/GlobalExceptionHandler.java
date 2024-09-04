package com.jobportal.jobportal.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OfferDoesNotExistsException.class)
    public ProblemDetail handleOfferDoesNotExistsException(OfferDoesNotExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("Item Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }
}
