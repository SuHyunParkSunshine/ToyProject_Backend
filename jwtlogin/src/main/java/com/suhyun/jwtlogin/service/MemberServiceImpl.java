package com.suhyun.jwtlogin.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suhyun.jwtlogin.dto.JwtTokenDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // @Autowired
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtTokenDto signIn(String userEmail, String userPassword) {
        // 1. userEmail + uesrPassword 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail,
                userPassword);

        try {
        // 2. 실제 검증. authenticate() 매서드를 통해 요청된 Member에 대한 검증 진행
        // authenticate 매서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 매서드
        // 실행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // 3. 인증 정보를 기반으로 UserDetails 객체 생성
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 4. 인증 정보를 기반으로 JWT 토큰 생성
            JwtTokenDto jwtToken = jwtTokenProvider.generateToken(userDetails);

            return jwtToken;
         } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
         }
        return null;

    }

}
