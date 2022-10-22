package com.kodilla.sportscentrefront.config;

import com.kodilla.sportscentrefront.exceptions.ErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new ErrorHandler())
                .build();
    }
}
