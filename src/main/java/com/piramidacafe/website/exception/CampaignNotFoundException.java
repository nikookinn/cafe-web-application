package com.piramidacafe.website.exception;

public class CampaignNotFoundException extends RuntimeException{
    public CampaignNotFoundException(String message) {
        super(message);
    }
}
