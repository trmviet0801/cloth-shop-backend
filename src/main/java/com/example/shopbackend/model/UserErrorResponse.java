package com.example.shopbackend.model;

import org.springframework.http.HttpStatus;

import java.sql.Time;

public class UserErrorResponse {
    private String message;
    private HttpStatus status;
    private long timestamp;
    public UserErrorResponse(){};
    public UserErrorResponse(String message, HttpStatus status, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
