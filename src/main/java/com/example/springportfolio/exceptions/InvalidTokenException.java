package com.example.springportfolio.exceptions;

import java.io.IOException;

public class InvalidTokenException extends IOException {

    public InvalidTokenException(String message){
        super("invalid token exception: "+message);
    }
}
