package com.example.coursemanagement.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder()
            .group("Student")
            .pathsToMatch("/api/**")
            .build();
    }
}
