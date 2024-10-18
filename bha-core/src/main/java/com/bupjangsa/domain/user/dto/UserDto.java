package com.bupjangsa.domain.user.dto;

import com.bupjangsa.domain.user.entity.User;
import com.bupjangsa.type.AuthorityType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

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
                    .authority(AuthorityType.MEMBER)
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
        private AuthorityType authority;

        public static UserInfo from(User user) {
            return UserInfo.builder()
                    .userId(user.getUserId())
                    .accountId(user.getAccountId())
                    .userName(user.getUserName())
                    .password(user.getPassword())
                    .authority(user.getAuthority())
                    .build();
        }
    }

}
