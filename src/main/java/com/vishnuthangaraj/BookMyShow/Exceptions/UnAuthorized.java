package com.vishnuthangaraj.BookMyShow.Exceptions;

import org.springframework.stereotype.Component;

@Component
public class UnAuthorized extends RuntimeException{
    public UnAuthorized(String message){
        super(message);
    }
}
