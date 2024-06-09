package com.epson.poni.error.exception;

import com.epson.poni.error.ErrorCode;

public class NotFoundException extends BaseException {
    private final ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode){
        super(errorCode);
        this.errorCode = errorCode;
    }
}