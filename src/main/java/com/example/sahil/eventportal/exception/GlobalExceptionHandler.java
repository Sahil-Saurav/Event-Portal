package com.example.sahil.eventportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorEntity> ResourceNotFound(ResourceNotFoundException e){
        ErrorEntity response = new ErrorEntity(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
