package com.highgag.sbook.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.highgag.sbook.common.dto.AuthorizationDto;
import com.highgag.sbook.common.jwt.JwtProperties;
import com.highgag.sbook.common.jwt.JwtTokenProvider;
import com.highgag.sbook.error.DuplicatedUserException;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
    }

    public String issueToken(User user){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.createToken(user);
        return token;
    }

    private void validateDuplicateMember(User user){
        Optional<User> findMember = userRepository.findByEmail(user.getEmail());
        if (findMember.isPresent()){
            throw new DuplicatedUserException();
        }
    }
}
