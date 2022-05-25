package com.highgag.sbook.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.highgag.sbook.common.auth.PrincipalDetails;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private final Logger log = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;
    JwtProperties jwtProperties = JwtProperties.getInstance();

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties, UserRepository userRepository) {
        super(authenticationManager);
        this.jwtProperties = jwtProperties;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(jwtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(jwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(jwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        String email = JWT.require(Algorithm.HMAC512(jwtProperties.getSECRET())).build().verify(token)
                .getClaim("email").asString();
        String username = JWT.require(Algorithm.HMAC512(jwtProperties.getSECRET())).build().verify(token)
                .getClaim("username").asString();
        Long id = JWT.require(Algorithm.HMAC512(jwtProperties.getSECRET())).build().verify(token)
                .getClaim("id").asLong();

        if(email != null) {
            Optional<User> userEntity = userRepository.findByEmail(email);
            try{
                User user = userEntity.get();
                PrincipalDetails principalDetails = new PrincipalDetails(user);
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                principalDetails,
                                null,
                                principalDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (NullPointerException e) {
            }
        }
        chain.doFilter(request, response);
    }

}