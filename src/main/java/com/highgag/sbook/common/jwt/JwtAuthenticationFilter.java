package com.highgag.sbook.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highgag.sbook.common.auth.PrincipalDetails;
import com.highgag.sbook.common.dto.AuthorizationDto;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


/**
 * login 요청(post) 시 username(email), password 전송하면
 * UsernamePasswordAuthenticationFilter 동작
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * login 요청을 하면 로그인 시도를 위해서 실행 -> email, password 확인 작업
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * attemptAuthentication 실행 후 인증이 정상적으로 된 경우 아래 함수 실행
     * JWT 토큰 발급 -> request한 사용자에게 JWT response body에 담아 보내기
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        User user = principalDetails.getUser();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        AuthorizationDto authorizationDto = new AuthorizationDto(user.getUsername(), jwtToken);
        String jsonResponse = mapper.writeValueAsString(generalResponse.setData("200", "정상적으로 토큰이 발급되었습니다", authorizationDto));

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        String jsonResponse = mapper.writeValueAsString(generalResponse.setData("401", "Unauthorized", failed.getMessage()));

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonResponse);
    }
}
