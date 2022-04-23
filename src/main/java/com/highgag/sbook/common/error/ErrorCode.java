package com.highgag.sbook.common.error;

public enum ErrorCode {

    UNAUTHORIZED(401, "유효하지 않은 토큰입니다"),
    FORBIDDEN(403, "권한이 없는 정보입니다"),
    NOT_FOUND(404, "존재하지 않는 데이터입니다"),
    CONFLICT(409, "중복된 회원이 존재합니다");

    private final String message;
    private final int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
