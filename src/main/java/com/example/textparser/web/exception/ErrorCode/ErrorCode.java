package com.example.textparser.web.exception.ErrorCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("C001", "Server Error", 500),
    NO_RESULT("P001", "No text", 400)
    ;

    @Getter
    private final String code;
    @Getter
    private final String message;
    @Getter
    private int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
