package com.example.springportfolio.exceptions;

import java.io.IOException;

public class NotFoundResourceException extends IOException {
    public NotFoundResourceException(String message){
        super("not found resource: "+message);
    }
}
