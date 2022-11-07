package com.example.springportfolio.exceptions;

import java.io.IOException;

public class InvalidPasswordException extends IOException {
    public InvalidPasswordException(String message){
        super("invalid password Exception: "+message);
    }
}
