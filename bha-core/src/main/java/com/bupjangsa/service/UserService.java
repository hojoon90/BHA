package com.bupjangsa.service;

import com.bupjangsa.domain.user.UserInfo;
import com.bupjangsa.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository 에서 데이터 처리 후 DTO를 리턴합니다.
 *
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public String registUser(UserInfo userInfo){
        String result;
        try{
            userRepository.save(userInfo);
            result = "Success";
        }catch(Exception e){
            result = "Fail";
        }

        return result;
    }

    public String updateUser(UserInfo userInfo){
        String result;
        try{
            userRepository.findById(Long.parseLong(userInfo.getUserId()))
                    .orElseThrow(() -> new RuntimeException(""));

            //TODO 업데이트 로직 추가

            result = "Success";
        }catch(Exception e){
            result = "Fail";
        }

        return result;
    }

    public UserInfo getUser(String userId){
        return userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new RuntimeException(""));
    }

}
