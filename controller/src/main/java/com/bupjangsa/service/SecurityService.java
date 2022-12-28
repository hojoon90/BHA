package com.bupjangsa.service;

import com.bupjangsa.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public String registUser(UserInfo userInfo){
        //TODO 사용자 가입 여부 판단
        // 패스워드 인코딩
        // 사용자 등록
        // 실패 시 실패 코드 전달.

        return "OK";
    }



}
