package com.alami.cooperation.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class BaseException extends Exception {

    private String message;
    private HttpStatus httpStatus;

    public BaseException(String message) {
        this.message = message;
        httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BaseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
