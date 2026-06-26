package com.mhj.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI crudOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD Enterprise API")
                        .description("API REST de produtos construída com Java 25, Spring Boot 4, PostgreSQL, Flyway, MapStruct e JPA Specification.")
                        .version("0.3.0-SNAPSHOT")
                        .contact(new Contact()
                                .name("Manuel Hinojosa")));
    }
}