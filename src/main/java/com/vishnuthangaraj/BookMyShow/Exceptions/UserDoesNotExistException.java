package com.vishnuthangaraj.BookMyShow.Exceptions;

import org.springframework.stereotype.Component;

@Component
public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String message){
        super(message);
    }
}
