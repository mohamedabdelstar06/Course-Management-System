package com.example.coursemanagement.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi instructorApi() {
        return GroupedOpenApi.builder()
            .group("Instructor")
            .pathsToMatch("/api/**")
            .build();
    }
}
