package com.example.springportfolio.exceptions;

import java.io.IOException;

public class LengthException extends IOException {
    public LengthException(String message){
        super("length exception: "+message);
    }
}
