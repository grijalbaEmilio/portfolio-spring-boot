package com.example.springportfolio.exceptions;

import java.io.IOException;

public class InvalidModifyData extends IOException {
    public InvalidModifyData(String message){
        super("invalid modify data: "+message);
    }
}
