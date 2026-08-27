package com.example.sahil.eventportal.exception;

public class UnAuthorizedAccess extends RuntimeException {

    public UnAuthorizedAccess(String message) {
        super(message);
    }
}
