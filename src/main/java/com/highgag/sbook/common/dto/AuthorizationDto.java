package com.highgag.sbook.common.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.*;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
public class AuthorizationDto {
    String code;
    String msg;
    String token;

    public AuthorizationDto(String code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = "Bearer "+ token;
    }
}
