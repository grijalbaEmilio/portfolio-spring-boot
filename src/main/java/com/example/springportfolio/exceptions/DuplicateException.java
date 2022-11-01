package com.example.springportfolio.exceptions;

import java.io.IOException;

public class DuplicateException extends IOException {

    public DuplicateException(String message){
        super("no duplicate exception: "+message);
    }
}
