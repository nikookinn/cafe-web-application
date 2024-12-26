package com.piramidacafe.website.config.appConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:my-private.properties")
public class AppConfig {
}
