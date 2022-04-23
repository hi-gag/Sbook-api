package com.highgag.sbook.user.controller;

import com.highgag.sbook.common.dto.GeneralResponse;
import com.highgag.sbook.common.error.DuplicatedUserException;
import com.highgag.sbook.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.highgag.sbook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
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
    public GeneralResponse join(@Valid @RequestBody User user){
        try {
            user.saveEntity(user.getUsername(), user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
            return new GeneralResponse("200", "회원가입이 완료되었습니다");
        } catch (DuplicatedUserException e){
            return new GeneralResponse("409", "중복된 회원이 존재합니다");
        }
    }
}
