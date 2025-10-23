package com.example.stockmarketspringapi.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
