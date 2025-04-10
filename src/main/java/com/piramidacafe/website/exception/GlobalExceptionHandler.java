package com.piramidacafe.website.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MenuNotFoundException.class,
            CampaignNotFoundException.class,
            CategoryNotFoundException.class,
            ContactInfoNotFoundException.class,
            ItemNotFoundException.class})
    public String handleEntityNotFoundException(Exception ex, Model model) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", ex.getMessage());
        return "errors/error-page";
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public String handleRateLimitException(RateLimitExceededException ex, Model model) {
        log.warn("Rate limit exceeded: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "errors/rate-limit-error";
    }


}
