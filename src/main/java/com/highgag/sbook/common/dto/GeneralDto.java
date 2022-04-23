package com.highgag.sbook.common.dto;

public class GeneralDto<T> {
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
