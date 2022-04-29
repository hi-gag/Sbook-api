package com.highgag.sbook.user.controller;

import com.highgag.sbook.common.dto.AuthorizationDto;
import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.common.jwt.JwtTokenProvider;
import com.highgag.sbook.error.DuplicatedUserException;
import com.highgag.sbook.error.InvalidParameterException;
import com.highgag.sbook.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("user/signup")
    public GeneralResponse join(@Valid @RequestBody User user, BindingResult result){
        GeneralResponse<Object> response = new GeneralResponse<>();
        if (result.hasErrors()){
            throw new InvalidParameterException(result);
        }
        try {
            user.saveEntity(user.getUsername(), user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
            String token = userService.issueToken(user);
            response.setData("200", "회원가입이 완료되었습니다", new AuthorizationDto(user.getUsername(), token));
            return response;
        } catch (DuplicatedUserException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }
}
