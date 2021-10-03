package com.alami.cooperation.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> success(String message, Object data) {
        return success(message, data, HttpStatus.OK);
    }

    public static ResponseEntity<Object> success(String message, Object data, HttpStatus httpStatus) {
        return createResponse(true, message, data, null, httpStatus);
    }

    public static ResponseEntity<Object> error(String message, Object data, HttpStatus httpStatus) {
        return createResponse(false, message, null, data, httpStatus);
    }

    public static ResponseEntity<Object> createResponse(boolean success, String message, Object data, Object error, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", message);
        response.put("data", data);
        response.put("error", error);
        return new ResponseEntity<>(response, httpStatus);
    }

}
