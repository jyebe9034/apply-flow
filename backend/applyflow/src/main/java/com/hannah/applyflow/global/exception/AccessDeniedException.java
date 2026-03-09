package com.hannah.applyflow.global.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("You do not have permission to access this job");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
