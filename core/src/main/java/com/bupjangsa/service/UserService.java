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
        userPersister.registUser(userInfo);

        return "";
    }

    public String updateUser(UserInfo userInfo){
        userPersister.updateUser(userInfo);

        return "";
    }

    public UserInfo getUser(String userId){
        return userPersister.getUser(userId);
    }

}
