package com.cebrailerdogan.cvrservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingRequiredParametersException extends RuntimeException{

    public MissingRequiredParametersException(String message){
        super(message);
    }
}
