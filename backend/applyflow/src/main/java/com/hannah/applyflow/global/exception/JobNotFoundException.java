package com.hannah.applyflow.global.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(Long id) {
        super("Job not found by id: " + id);
    }
}
