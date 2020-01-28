package com.example.textparser.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;

    public ErrorResponse(int status) {
        this.status = status;
    }

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
