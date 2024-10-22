package com.pruebatecnica.apibackend.exception;

public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message) {
        super(message);
    }
}
