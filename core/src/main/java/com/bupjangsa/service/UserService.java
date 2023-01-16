package com.bupjangsa.service;

import com.bupjangsa.domain.UserInfo;
import com.bupjangsa.repository.UserPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserPersister userPersister;

    public String registUser(UserInfo userInfo){
        String result;
        try{
            userPersister.registUser(userInfo);
            result = "Success";
        }catch(Exception e){
            result = "Fail";
        }

        return result;
    }

    public String updateUser(UserInfo userInfo){
        String result;
        try{
            userPersister.updateUser(userInfo);
            result = "Success";
        }catch(Exception e){
            result = "Fail";
        }

        return result;
    }

    public UserInfo getUser(String userId){
        return userPersister.getUser(userId);
    }

}
