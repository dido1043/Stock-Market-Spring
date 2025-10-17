package com.example.stockmarketspringapi.exception;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String responseBody = extractResponseBody(response);
        logger.error("Feign client error. Method: {}, Status: {}, Body: {}",
                methodKey, status, responseBody);

        switch (status) {
            case BAD_REQUEST:
                return new IllegalArgumentException("Invalid request: " + responseBody);
            case UNAUTHORIZED:
                return new SecurityException("Unauthorized access");
            case FORBIDDEN:
                return new AccessDeniedException("Access forbidden");
            case NOT_FOUND:
                return new NotFoundException("Resource not found");
            case INTERNAL_SERVER_ERROR:
                return new RuntimeException("Internal server error");
            default:
                return new Exception("Unexpected error: " + responseBody);
        }
    }
    private String extractResponseBody(Response response) {
        if (response.body() == null) {
            return "No response body";
        }

        try {
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return "Error reading response body";
        }
    }
}
