package com.piramidacafe.website.config.securityConfig;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServletRequiestWrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
            @Override
            public String getRequestURI() {
                String requestURI = super.getRequestURI();
                if (requestURI.endsWith("/")) {
                    return requestURI.substring(0, requestURI.length() - 1);
                }
                return requestURI;
            }
        };
        filterChain.doFilter(requestWrapper, servletResponse);
    }
}
