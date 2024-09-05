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

    @ExceptionHandler(UserDoesNotExistException.class)
    public ProblemDetail handleUserDoesNotExistException(UserDoesNotExistException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("User Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(TechnologyDoesNotExistsException.class)
    public ProblemDetail handleTechnologyDoesNotExistException(TechnologyDoesNotExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("Technology Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(ExperienceDoesNotExistsException.class)
    public ProblemDetail handleExperienceDoesNotExistException(ExperienceDoesNotExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("Experience Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(EmploymentTypeDoesNotExistsException.class)
    public ProblemDetail handleEmploymentTypeDoesNotExistException(EmploymentTypeDoesNotExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("EmploymentType Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(WorkTypeDoesNotExistsException.class)
    public ProblemDetail handleWorkTypeDoesNotExistException(WorkTypeDoesNotExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("WorkType Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }
}
