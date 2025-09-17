package com.santiago.flightsapp.flights_app.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(Long id){
        super("User with id " + id + " not found");
    }
}