package com.piramidacafe.website.exception;

public class MissingUserAgentException extends RuntimeException {
    public MissingUserAgentException(String message) {
        super(message);
    }
}
