package com.suhyun.jwtlogin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {

    private String userEmail;
    private String userPassword;
    
}
