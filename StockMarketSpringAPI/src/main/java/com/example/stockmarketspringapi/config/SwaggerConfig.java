package com.example.stockmarketspringapi.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig{
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Stock Market Spring Version")
                        .version("1.0")
                        .description("Stock Market Spring Boot REST API"));
    }
}
