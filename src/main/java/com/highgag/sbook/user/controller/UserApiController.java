package com.highgag.sbook.user.controller;

import com.highgag.sbook.common.dto.GeneralResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("join")
    public GeneralResponse join(@Valid @RequestBody User user){
        user.saveUser(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new GeneralResponse("200", "회원가입 완료");
    }

    @GetMapping("api/v1/user")
    public String user(){
        return "user1";
    }

    @GetMapping("api/v1/user/1")
    public String user1(){
        return "user1";
    }
    @GetMapping("api/v1/user/2")
    public String user2(){
        return "user1";
    }
}
