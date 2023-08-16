package com.suhyun.jwtlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    
    private boolean result;
    private String msg;
    private D data;

    // 회원가입 성공했을 때 반환하는 ResponseDto
    public static <D> ResponseDto<D> setSuccess(String msg, D data) {
        return ResponseDto.set(true, msg, data);
    }
    // 회원가입 실패했을 때 반환하는 ResponseDto
    public static <D> ResponseDto<D> setFailed(String msg) {
        return ResponseDto.set(false, msg, null);
    }
}
