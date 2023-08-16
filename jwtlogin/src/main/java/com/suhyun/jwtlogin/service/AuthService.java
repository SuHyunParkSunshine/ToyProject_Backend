package com.suhyun.jwtlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suhyun.jwtlogin.domain.Member;
import com.suhyun.jwtlogin.dto.ResponseDto;
import com.suhyun.jwtlogin.dto.SignUpDto;
import com.suhyun.jwtlogin.persistence.MemberRepository;

@Service
public class AuthService {

    @Autowired
    MemberRepository memRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    public ResponseDto<?> signUp(SignUpDto dto) {
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();
        String userEmail = dto.getUserEmail();

        // email 중복 확인, 레포지토리 사용시에는 try & catch 문으로 싸줘야 함.
        try {
            if(memRepo.existsById(userEmail))
            return ResponseDto.setFailed("Exist Eamil!!");
        } catch(Exception e) {
            return ResponseDto.setFailed("Data Base Error!!");
        }

        // 비밀번호가 서로 다르면 failed response 반환!
        if (!userPassword.equals(userPasswordCheck))
        return ResponseDto.setFailed("Password is not matched");

        // 비밀번호 암호화 및 default값 설정
        dto.setUserPassword(encoder.encode(dto.getUserPassword()));
        dto.setUesrRole("ROLE_USER");
        dto.setUserEnabled(true);
        // Member 객체 생성
        Member member = new Member(dto);

        // MemberRepository를 이용해서 데이터베이스에 Member 저장!!
        try {
            memRepo.save(member);
        } catch (Exception e) {
            return ResponseDto.setFailed("Data Base Error");
        }

        // 성공 시 success response 반환
        return ResponseDto.setSuccess("Sign Up Success", null);
    }    
}
