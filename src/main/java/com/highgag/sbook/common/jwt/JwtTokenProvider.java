package com.highgag.sbook.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.highgag.sbook.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    JwtProperties jwtProperties = JwtProperties.getInstance();
    //토큰생성
    public String createToken(User user) {
        String jwtToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ jwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC512(jwtProperties.getSECRET()));
        return jwtToken;
    }
}
