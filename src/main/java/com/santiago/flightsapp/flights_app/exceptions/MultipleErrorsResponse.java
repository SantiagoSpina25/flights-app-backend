package com.santiago.flightsapp.flights_app.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class MultipleErrorsResponse {
    private String timestamp;
    private int status;
    private Map<String, String> errors;
    private String message;

    public MultipleErrorsResponse(int status, Map<String, String> errors, String message) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
