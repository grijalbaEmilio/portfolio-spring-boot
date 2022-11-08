package com.example.springportfolio.exceptions;

import java.io.IOException;

public class NotAuthorizedException extends IOException {

    public NotAuthorizedException(String message){
        super("invalid token exception: "+message);
    }
}
