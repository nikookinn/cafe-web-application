package com.piramidacafe.website.exeption;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MenuNotFoundException.class)
    public String handleMenuNotFoundException(MenuNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "dashboard/error-page";
    }
}
