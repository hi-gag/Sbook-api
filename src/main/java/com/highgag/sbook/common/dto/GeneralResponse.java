package com.highgag.sbook.common.dto;

import lombok.Getter;

@Getter
public class GeneralResponse {
    String code;
    String msg;

    public GeneralResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
