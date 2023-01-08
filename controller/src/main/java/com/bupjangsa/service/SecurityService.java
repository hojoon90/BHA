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
        UserInfo originUser = this.selectUserInfo(userInfo.getUserId());
        if(originUser != null) return "Exist User";
        // 패스워드 인코딩
        userInfo.setSecretKey(passwordEncoder.encode(userInfo.getSecretKey()));
        // 사용자 등록
        userService.registUser(userInfo);
        // 실패 시 실패 코드 전달.

        return "OK";
    }

    public String updateUser(UserInfo userInfo){
        //사용자 정보 수정
        String newPassword = "";
        if(userInfo.getSecretKey() != null && !userInfo.getSecretKey().equals(""))
            newPassword = passwordEncoder.encode(userInfo.getSecretKey());

        if(!newPassword.equals("")){
            UserInfo originUser = this.selectUserInfo(userInfo.getUserId());
            String pastPassword = originUser.getSecretKey();

            if(!pastPassword.equals(newPassword)) userInfo.setSecretKey(newPassword);
        }

        userService.updateUser(userInfo);

        return "OK";
    }

    public UserInfo selectUserInfo(String userId){
        return userService.getUser(userId);
    }


}
