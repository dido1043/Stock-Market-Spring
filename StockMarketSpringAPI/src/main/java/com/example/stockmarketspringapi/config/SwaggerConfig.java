package com.example.stockmarketspringapi.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    private static final String OAUTH_SCHEME = "oauth2";
    private static final String BEARER_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        OAuthFlow authorizationCodeFlow = new OAuthFlow()
                .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUrl("https://oauth2.googleapis.com/token")
                .scopes(new Scopes()
                        .addString("read", "Read access")
                        .addString("write", "Write access")
                        .addString("openid", "OpenID Connect scope"));

        OAuthFlows oauthFlows = new OAuthFlows().authorizationCode(authorizationCodeFlow);

        SecurityScheme oauthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("OAuth2 Authorization Code")
                .flows(oauthFlows);

        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Bearer token");

        Components components = new Components()
                .addSecuritySchemes(OAUTH_SCHEME, oauthScheme)
                .addSecuritySchemes(BEARER_SCHEME, bearerScheme);

        SecurityRequirement oauthRequirement = new SecurityRequirement()
                .addList(OAUTH_SCHEME, Arrays.asList("read", "write"));
        SecurityRequirement bearerRequirement = new SecurityRequirement()
                .addList(BEARER_SCHEME);

        return new OpenAPI()
                .components(components)
                .addSecurityItem(oauthRequirement)
                .addSecurityItem(bearerRequirement)
                .info(new Info().title("Stock Market Spring Version")
                        .version("1.0")
                        .description("Stock Market Spring Boot REST API"));
    }
}