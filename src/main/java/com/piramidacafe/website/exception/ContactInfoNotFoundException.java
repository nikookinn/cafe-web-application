package com.piramidacafe.website.exception;

public class ContactInfoNotFoundException extends RuntimeException {
    public ContactInfoNotFoundException(String message) {
        super(message);
    }
}
