package com.in28menutes.rest.webservices.restfulweb0services.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
    // timestamp
    private LocalDateTime timestamp;
    // message
    private String message;
    // details
    private String details;

    // 생성자
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // 게터 생성, 세터는 따로 생성하지 않는다
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
