package com.bupjangsa.repository;

import com.bupjangsa.domain.UserInfo;

public interface UserPersister {
    void registUser(UserInfo userInfo);
}
