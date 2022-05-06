package com.highgag.sbook.error;

public class ForbiddenException extends CustomException {
    private static final long serialVersionUID = -2116671122895194101L;

    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
