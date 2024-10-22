package com.pruebatecnica.apibackend.exception.handler;

import com.pruebatecnica.apibackend.exception.RequiredFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<String> handleRequiredFieldException(RequiredFieldException exception){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            org.springframework.http.converter.HttpMessageConversionException.class,
            jakarta.validation.ConstraintViolationException.class
    })
    @ResponseBody
    public String handleBadRequest(Exception exception){
        return exception.getMessage();
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            HttpClientErrorException.Forbidden.class
    })
    @ResponseBody
    public String handleForbidden(Exception exception){
        return exception.getMessage();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            jakarta.persistence.EntityNotFoundException.class
    })
    @ResponseBody
    public String handleEntityNotFound(Exception exception){
        return exception.getMessage();
    }
}
