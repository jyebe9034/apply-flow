package com.hannah.applyflow.global.exception;

public class AccessDeniedException extends CustomException {

    public AccessDeniedException() {
        super(ErrorCode.ACCESS_DENIED);
    }
}
