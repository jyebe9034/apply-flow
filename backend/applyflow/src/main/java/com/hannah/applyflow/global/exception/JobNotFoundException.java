package com.hannah.applyflow.global.exception;

public class JobNotFoundException extends CustomException {

    public JobNotFoundException() {
        super(ErrorCode.JOB_NOT_FOUND);
    }
}
