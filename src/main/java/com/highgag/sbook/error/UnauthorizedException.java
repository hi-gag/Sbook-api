package com.highgag.sbook.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UnauthorizedException extends CustomException {
    private static final long serialVersionUID = -2116671122895194101L;

    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);

    }
}
