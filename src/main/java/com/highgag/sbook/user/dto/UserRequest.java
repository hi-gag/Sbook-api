package com.highgag.sbook.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserRequest {
    @NotBlank
    String email;

    @NotBlank
    String password;

    public UserRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
