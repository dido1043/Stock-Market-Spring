package com.example.stockmarketspringapi.model.dto.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
public class ErrorResponse {
    private String message;
    private int status;
    private Instant timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
