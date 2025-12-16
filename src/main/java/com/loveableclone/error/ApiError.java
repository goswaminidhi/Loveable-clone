package com.loveableclone.error;


import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiError (
    HttpStatus httpStatus,
    String message,
    Instant timestamp
){
    public ApiError(HttpStatus httpStatus, String message) {
        this(httpStatus,message,Instant.now());
    }
}
