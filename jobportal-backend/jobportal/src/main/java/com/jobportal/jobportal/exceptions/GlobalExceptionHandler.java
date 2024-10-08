package com.jobportal.jobportal.exceptions;

import com.jobportal.jobportal.exceptions.authority.AuthorityDoesNotExistException;
import com.jobportal.jobportal.exceptions.offer.*;
import com.jobportal.jobportal.exceptions.payment.PaymentStripePriceDoesNotExistException;
import com.jobportal.jobportal.exceptions.user.FavouriteOfferAlreadyExistsException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(AuthorityDoesNotExistException.class)
    public ProblemDetail handleAuthorityDoesNotExistException(AuthorityDoesNotExistException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("Authority not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed for one or more fields.");

        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("hostname", "localhost");

        return problemDetail;
    }

    @ExceptionHandler(FavouriteOfferAlreadyExistsException.class)
    public ProblemDetail handleFavouriteOfferAlreadyExists(FavouriteOfferAlreadyExistsException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getLocalizedMessage());
        body.setTitle("FavouriteOffer is already exists");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(InvalidSortByParameterException.class)
    public ProblemDetail handleFavouriteOfferAlreadyExists(InvalidSortByParameterException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getLocalizedMessage());
        body.setTitle("Bad sortBy Parameter");
        body.setProperty("hostname", "localhost");
        return body;
    }

    @ExceptionHandler(PaymentStripePriceDoesNotExistException.class)
    public ProblemDetail handlePaymentStripePriceDoesNotExistException(PaymentStripePriceDoesNotExistException ex){
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getLocalizedMessage());
        body.setTitle("Payment Stripe Price Not Found");
        body.setProperty("hostname", "localhost");
        return body;
    }
}
