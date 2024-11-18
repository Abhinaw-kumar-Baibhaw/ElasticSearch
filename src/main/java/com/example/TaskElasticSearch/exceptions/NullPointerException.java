package com.example.TaskElasticSearch.exceptions;

import com.example.TaskElasticSearch.enums.ErrorCode;

public class NullPointerException extends RuntimeException{

    ErrorCode errorCode;

    public NullPointerException(ErrorCode errorCode) {
        this.errorCode=errorCode;
    }
}
