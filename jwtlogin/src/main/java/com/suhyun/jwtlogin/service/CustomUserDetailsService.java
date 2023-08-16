package com.suhyun.jwtlogin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suhyun.jwtlogin.domain.Member;
import com.suhyun.jwtlogin.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memRepo;
    // private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Member> opt = memRepo.findById(userEmail);
        if(!opt.isPresent()) {
            new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다.");
            return null;
        }
        Member member = opt.get();
        System.out.println("CustomUserDetailsService-loadUserByUsername :" + member);
        return new User(member.getUserEmail(), member.getUserPassword(), member.getAuthorities());                 
                 
    }
    
    // 해당하는 User의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    // private UserDetails createUserDetails(Member member) {
    //     return User.builder()
    //             .username(member.getUserEmail())
    //             .password(passwordEncoder.encode(member.getUserPassword()))
    //             .roles(member.getRole())
    //             .build();
    // }
}
