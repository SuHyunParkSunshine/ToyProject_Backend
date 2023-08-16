package com.suhyun.jwtlogin.service;

import com.suhyun.jwtlogin.dto.JwtTokenDto;

public interface MemberService {

    JwtTokenDto signIn(String userEmail, String userPassword);

}
