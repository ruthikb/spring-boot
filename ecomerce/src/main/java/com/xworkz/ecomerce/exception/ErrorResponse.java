package com.xworkz.ecomerce.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
    }
    public ErrorResponse(){
        this.timestamp= LocalDateTime.now();
    }
}
