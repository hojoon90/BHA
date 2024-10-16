package com.bupjangsa.domain.user.dto;

import com.bupjangsa.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    private UserDto(){/*Do Nothing*/}

    @Getter
    @Builder
    public static class Register{
        private String accountId;
        private String password;
        private String userName;

        public User toEntity(){
            return User.builder()
                    .accountId(accountId)
                    .password(password)
                    .userName(userName)
                    .build();
        }

    }

    @Getter
    @Builder
    public static class Update{
        private Long id;
        private String name;

    }


    @Getter
    @Builder
    public static class UserInfo{
        private Long userId;
        private String accountId;
        private String password;
        private String userName;

        public static UserInfo of(User user) {
            return UserInfo.builder()
                    .userId(user.getUserId())
                    .accountId(user.getAccountId())
                    .userName(user.getUserName())
                    .password(user.getPassword())
                    .build();
        }
    }

}
