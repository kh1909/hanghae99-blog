package com.sparta.project03.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
}