package com.piramidacafe.website.config.appConfig;

import com.piramidacafe.website.interceptor.RateLimitInterceptor;
import com.piramidacafe.website.interceptor.UserAgentInterceptor;
import com.piramidacafe.website.interceptor.VisitorCounterInterceptor;
import com.piramidacafe.website.converter.StringToSimpleCategoryDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final VisitorCounterInterceptor visitorCounterInterceptor;
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

        registry.addInterceptor(visitorCounterInterceptor);
    }
}
