package com.example.springportfolio.exceptions;

import java.io.IOException;

public class NotFoundResource extends IOException {
    public NotFoundResource(String message){
        super("not found resource: "+message);
    }
}
