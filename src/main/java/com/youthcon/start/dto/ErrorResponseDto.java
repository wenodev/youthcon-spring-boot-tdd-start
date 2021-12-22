package com.youthcon.start.dto;

public class ErrorResponseDto {
    private String message;

    public String getMessage() {
        return message;
    }

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
