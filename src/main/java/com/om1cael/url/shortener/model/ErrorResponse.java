package com.om1cael.url.shortener.model;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final boolean success;

    public ErrorResponse(String message) {
        this.message = message;
        this.success = false;
    }
}
