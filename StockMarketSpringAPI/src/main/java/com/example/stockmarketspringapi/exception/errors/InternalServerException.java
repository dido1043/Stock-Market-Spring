package com.example.stockmarketspringapi.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    private final int status;

    public InternalServerException(String message) {
        super(message);
        this.status = 500;
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
        this.status = 500;
    }

    public InternalServerException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
