package com.piramidacafe.website.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("errorCode", statusCode);

            switch (statusCode) {
                case 404:
                    model.addAttribute("errorMessage", "The page you're looking for is not found.");
                    break;
                case 500:
                    model.addAttribute("errorMessage", "We're experiencing some technical issues. Please try again later.");
                    break;
                case 403:
                    model.addAttribute("errorMessage", "Access to this page is forbidden.");
                    break;
                default:
                    model.addAttribute("errorMessage", "An unexpected error occurred. Please contact support.");
                    break;
            }
        }
        return "errors/error";
    }
}
