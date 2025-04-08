package com.piramidacafe.website.aspect;

import com.piramidacafe.website.service.VisitorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@RequiredArgsConstructor
@Slf4j
public class VisitorCounterInterceptor implements HandlerInterceptor {

    private final VisitorService visitorService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String ipAddress = getClientIp(request);

        if (!requestURI.contains("/static/")
                && !requestURI.contains("/assets/")
                && !requestURI.contains("/api/")
                && !requestURI.contains("/favicon.ico")
                && !requestURI.contains("/admin")
                && !requestURI.endsWith(".css")
                && !requestURI.endsWith(".js")
                && !requestURI.endsWith(".png")
                && !requestURI.endsWith(".jpg")
                && !requestURI.endsWith(".jpeg")
                && !requestURI.endsWith(".woff")
                && !requestURI.endsWith(".ttf")) {

            boolean isNewVisitor = visitorService.incrementVisitorCount(request, response);
            if (isNewVisitor){
                log.info("New Visitor!!! ip address is : "+ipAddress);
            }
        }

        return true;
    }

    public String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Forwarded-For");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }
}
