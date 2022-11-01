package com.example.springportfolio.exceptions;

import java.io.IOException;

public class InvalidTypeException extends IOException {

    public InvalidTypeException(String message){
        super("invalid file type: "+message);
    }
}
