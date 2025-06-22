package com.building.flood.common.exception;

import java.util.function.Supplier;

public class BusinessException extends RuntimeException  {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }

    public String getMessage() {
        return errorCode.getMessage();
    }
}
