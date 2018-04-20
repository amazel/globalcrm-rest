package com.globalcrm.rest.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorDetails {
    private LocalDateTime timestamp;
    private List<String> messages;
    private String exceptionDetails;

    public ErrorDetails(LocalDateTime timestamp, List<String> messages, String exceptionDetails) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.exceptionDetails = exceptionDetails;
    }
}