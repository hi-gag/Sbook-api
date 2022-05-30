package com.highgag.sbook.common.config;

import com.highgag.sbook.common.filter.CorsFilter;
import com.highgag.sbook.common.jwt.JwtAuthenticationFilter;
import com.highgag.sbook.common.jwt.JwtAuthorizationFilter;
import com.highgag.sbook.common.jwt.JwtProperties;
import com.highgag.sbook.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;


import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/user/login");

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 X
                .and()

                .formLogin().disable()
                .httpBasic().disable()

                .addFilter(authenticationFilter)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtProperties, userRepository))
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/bookmarks/**")
                .access("hasRole('ROLE_USER') ")
                .antMatchers("/bookmark/**")
                .access("hasRole('ROLE_USER') ")
                .anyRequest().permitAll();
    }
}
