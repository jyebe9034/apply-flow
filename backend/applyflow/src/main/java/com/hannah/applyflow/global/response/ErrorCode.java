package com.hannah.applyflow.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Auth
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "Email is already in use."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Authentication is required."),

    // Job
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "Job not found with given id."),

    // Common
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "You do not have permission."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Requested resource not found."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error has occurred.");

    private final HttpStatus status;
    private final String message;
}
