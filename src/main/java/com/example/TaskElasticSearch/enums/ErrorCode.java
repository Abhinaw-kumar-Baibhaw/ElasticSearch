package com.example.TaskElasticSearch.enums;

public enum ErrorCode {

    RESOURCE_NOT_FOUND("Resource not found"),
    GENERAL_ERROR("An unexpected error occurred"),
    CITY_NOT_FOUND("City not found"),
    ADDRESS_NOT_FOUND("Address not found"),
    TYPE_NOT_FOUND("Type not found");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
