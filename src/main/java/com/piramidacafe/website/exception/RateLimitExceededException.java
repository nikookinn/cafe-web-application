package com.piramidacafe.website.exception;

public class RateLimitExceededException extends RuntimeException{
    public RateLimitExceededException(String message) {
        super(message);
    }
}
