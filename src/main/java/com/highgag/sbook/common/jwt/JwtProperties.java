package com.highgag.sbook.common.jwt;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    static String SECRET;
    static int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    static String TOKEN_PREFIX = "Bearer ";
    static String HEADER_STRING = "Authorization";

    public static String getSECRET() {
        return SECRET;
    }

    @Value("${JWT_SECRET}")
    public void setSECRET(String value) {
        SECRET = value;
    }
}
