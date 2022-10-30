package com.example.springportfolio.exceptions;

import java.io.IOException;

public class NoDuplicateException extends IOException {

    public NoDuplicateException(String message){
        super("no duplicate exception: "+message);
    }
}
