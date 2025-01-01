package com.piramidacafe.website.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({MenuNotFoundException.class,
            CampaignNotFoundException.class,
            CategoryNotFoundException.class,
            ContactInfoNotFoundException.class,
            ItemNotFoundException.class})
    public String handleEntityNotFoundException(Exception ex, Model model) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", ex.getMessage());
        return "errors/error-page";
    }
}
