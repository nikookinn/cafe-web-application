package com.piramidacafe.website.exeption;

public class ContactInfoNotFoundException extends RuntimeException {
    public ContactInfoNotFoundException(String message) {
        super(message);
    }
}
