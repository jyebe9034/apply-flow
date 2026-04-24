package com.hannah.applyflow.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "https://applyflow.ink", description = "Domain Server"),
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Application Manager API")
                        .version("1.0.0")
                        .description("## Project Overview\n" +
                                "A REST API for a **Job Application Management System** designed to streamline the job search process.\n\n" +
                                "**Tech Stack:** Java 21, Spring Boot 4, Spring Data JPA. " +
                                "### Key Features\n" +
                                "* **Status Tracking**: Manage application stages using a structured `Enum` system (Applied, Interview, etc.).\n" +
                                "* **Upcoming Interviews**: A dedicated dashboard endpoint to retrieve a prioritized list of scheduled interviews.\n" +
                                "* **Application Analytics**: Statistical summaries of your job search progress.")
                        .contact(new Contact()
                                .name("Jihye Lim")
                                .url("https://github.com/jyebe9034")
                                .email("hannah.jihyelim@gmail.com")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
