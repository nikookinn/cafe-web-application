package com.piramidacafe.website.interceptor;

import com.piramidacafe.website.exception.MissingUserAgentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class UserAgentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null || userAgent.isEmpty()) {
            throw new MissingUserAgentException("Missing or invalid User-Agent header.");
        }
        return true;
    }
}
