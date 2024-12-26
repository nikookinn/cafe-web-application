package com.piramidacafe.website.exeption;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(String message){
        super(message);
    }
}
