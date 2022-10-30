package com.example.springportfolio.exceptions;

import java.io.IOException;

public class InvalidFileTypeException extends IOException {

    public InvalidFileTypeException(String message){
        super("invalid file type: "+message);
    }
}
