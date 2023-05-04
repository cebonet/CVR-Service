package com.cebrailerdogan.cvrservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Only one search parameter is accepted")
public class OnlyOneSearchParameterAllowedException extends RuntimeException {
    public OnlyOneSearchParameterAllowedException(String message){
        super(message);
    }
}
