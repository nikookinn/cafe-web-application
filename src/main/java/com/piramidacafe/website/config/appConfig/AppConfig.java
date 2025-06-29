package com.piramidacafe.website.config.appConfig;

import com.piramidacafe.website.interceptor.RateLimitInterceptor;
import com.piramidacafe.website.interceptor.UserAgentInterceptor;
import com.piramidacafe.website.converter.StringToSimpleCategoryDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig implements WebMvcConfigurer {

    private final RateLimitInterceptor rateLimitInterceptor;
    private final UserAgentInterceptor userAgentInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/app/images/")
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS).cachePublic());


        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS).cachePublic());
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSimpleCategoryDtoConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAgentInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/assets/**", "/images/**","/css/**","/favicon.ico");

        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/assets/**", "/images/**","/css/**","/favicon.ico");
    }

    @Bean
    public CommandLineRunner startMemoryMonitor() {
        return args -> {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                long heapSize = Runtime.getRuntime().totalMemory();
                long heapMaxSize = Runtime.getRuntime().maxMemory();
                long heapFreeSize = Runtime.getRuntime().freeMemory();
                long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                log.info("[MEMORY] Allocated Heap: " + (heapSize / 1024 / 1024) +
                        " MB" + " [MEMORY] Max Heap: " + (heapMaxSize / 1024 / 1024) +
                        " MB" + " [MEMORY] Free Heap:" + (heapFreeSize / 1024 / 1024) +
                        " MB"+ " [MEMORY] Used: " + (used / 1024 / 1024));
            }, 0, 12, TimeUnit.HOURS);
        };
    }
}
