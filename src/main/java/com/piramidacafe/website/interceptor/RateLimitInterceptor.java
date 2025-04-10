package com.piramidacafe.website.interceptor;

import com.piramidacafe.website.exception.RateLimitExceededException;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ip = getClientIp(request);
        String path = request.getRequestURI();
        Bucket bucket = buckets.computeIfAbsent(ip, this::newBucket);

        log.info("Request from IP: {}, Path: {}, User-Agent: {}",
                ip, path, request.getHeader("User-Agent"));

        if (bucket.tryConsume(1)) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(bucket.getAvailableTokens()));
            return true;
        } else {
            response.addHeader("Retry-After", String.valueOf(Duration.ofMinutes(1).getSeconds()));
            throw new RateLimitExceededException("Too many requests - please try again later.");
        }
    }

    private Bucket newBucket(String ip) {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(50).refillGreedy(50,Duration.ofMinutes(1)))
                .build();
    }
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0];
        }
        return ip;
    }


}
