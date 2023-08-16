package com.suhyun.jwtlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suhyun.jwtlogin.dto.JwtTokenDto;
import com.suhyun.jwtlogin.dto.ResponseDto;
import com.suhyun.jwtlogin.dto.SignInDto;
import com.suhyun.jwtlogin.dto.SignUpDto;
import com.suhyun.jwtlogin.service.AuthService;
import com.suhyun.jwtlogin.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    
    @Autowired
    MemberService memService;

    @Autowired
    AuthService authService;

    // 로그인
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        String userEmail= signInDto.getUserEmail();
        String userPassword = signInDto.getUserPassword();
        JwtTokenDto jwtToken = memService.signIn(userEmail, userPassword);
        log.info("request userEmail = {}, userPassword = {}", userEmail, userPassword);
        log.info("JwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken.getAccessToken()).build();
    }

    // 회원가입
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {       
        System.out.println(requestBody); // 잘 들어오는지 확인용
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

}
