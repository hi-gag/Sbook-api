package com.highgag.sbook.common.dto;

public class GeneralResponse<T> {
    public String code;
    public String message;
    private T data;

    public T getData() {
        return data;
    }

    public Object setData(String code, String msg) {
        this.code = code;
        this.message = msg;
        return this;
    }

    public Object setData(String code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
        return this;
    }
}
