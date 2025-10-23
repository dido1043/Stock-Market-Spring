package com.example.stockmarketspringapi.exception;

import com.example.stockmarketspringapi.exception.errors.BadRequestException;
import com.example.stockmarketspringapi.exception.errors.InternalServerException;
import com.example.stockmarketspringapi.exception.errors.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String responseBody = extractResponseBody(response);
        logger.error("Feign client error. Method: {}, Status: {}, Body: {}",
                methodKey, status, responseBody);

        switch (status) {
            case BAD_REQUEST:
                return new BadRequestException("Invalid request: " + responseBody);
            case UNAUTHORIZED:
                return new SecurityException("Unauthorized access");
            case FORBIDDEN:
                return new AccessDeniedException("Access forbidden");
            case NOT_FOUND:
                return new NotFoundException("Resource not found");
            case INTERNAL_SERVER_ERROR:
                return new InternalServerException("Internal server error: " + responseBody);
            case SERVICE_UNAVAILABLE:
            case BAD_GATEWAY:
            case GATEWAY_TIMEOUT:
                logger.error("Microservice is unavailable. Method: {}, Status: {}", methodKey, status);
                return new InternalServerException("Microservice is currently unavailable. Please try again later.");
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }

    /**
     * Handle connection failures and service unavailability
     * This method is called when Feign encounters connection issues
     */
    public Exception handleConnectionFailure(String methodKey, Exception cause) {
        logger.error("Feign connection failure. Method: {}, Error: {}", methodKey, cause.getMessage());
        
        if (cause instanceof java.net.ConnectException) {
            return new InternalServerException("Currency service is not available. Please check if the service is running.");
        } else if (cause instanceof java.net.SocketTimeoutException) {
            return new InternalServerException("Currency service request timed out. Please try again later.");
        } else if (cause instanceof java.net.UnknownHostException) {
            return new InternalServerException("Currency service host not found. Please check the service configuration.");
        } else if (cause instanceof java.io.IOException) {
            return new InternalServerException("Network error while connecting to currency service: " + cause.getMessage());
        } else {
            return new InternalServerException("Unexpected error connecting to currency service: " + cause.getMessage());
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
