package com.suhyun.jwtlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.suhyun.jwtlogin.config.filter.JwtAuthenticationFilter;
import com.suhyun.jwtlogin.service.JwtTokenProvider;
import com.suhyun.jwtlogin.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
        
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean // 블로그랑 코드 다르고 문제가 좀 있었음
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        // REST API이므로 basic auth 및 csrf 보안을 사용하지 않음
        .httpBasic(HttpBasicConfigurer::disable)
        .csrf(CsrfConfigurer::disable)
        .userDetailsService(customUserDetailsService)
        // JWT를 사용하기 때문에 세션을 사용하지 않음
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            // 해당 API에 대해서는 모든 요청을 허가            
            // USER 권한이 있어야 요청할 수 있음
            .requestMatchers("/members/**").authenticated()
            .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            // 이 밖에 모든 요청에 대해서 인증을 필요로 한다는 설정
            .anyRequest().permitAll()
        )
        // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
        UsernamePasswordAuthenticationFilter.class)
        .build();
    }

  
}
