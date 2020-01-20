package com.example.textparser.web.exception;

import com.example.textparser.web.exception.ErrorCode.ErrorCode;
import lombok.Getter;

@Getter
public class ParserException extends RuntimeException {
    public ParserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;
}
