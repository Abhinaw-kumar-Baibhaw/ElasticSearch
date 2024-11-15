package com.example.TaskElasticSearch.exceptions;


import com.example.TaskElasticSearch.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

    ErrorCode errorCode;

    public ResourceNotFoundException( ErrorCode errorCode) {
        this.errorCode=errorCode;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
