// 클라이언트에 토큰을 보내기 위해 JwtTokenDto 생성

package com.suhyun.jwtlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenDto {

    private String grantType; //JWT에 대한 인증타입 >> "Bearer " 인증방식 사용예정
    private String accessToken;
    private String refreshToken;
    
}
