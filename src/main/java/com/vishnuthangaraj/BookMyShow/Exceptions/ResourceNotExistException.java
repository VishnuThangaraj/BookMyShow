package com.vishnuthangaraj.BookMyShow.Exceptions;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotExistException extends RuntimeException{
    public ResourceNotExistException(String message){
        super(message);
    }
}
