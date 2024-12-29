package com.piramidacafe.website.exeption;

public class CategoryNotFoundException  extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
