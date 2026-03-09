package com.hannah.applyflow.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "Job not found with given id"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "You do not have permission");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
