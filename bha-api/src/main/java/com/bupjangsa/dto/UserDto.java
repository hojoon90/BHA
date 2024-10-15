package com.bupjangsa.dto;

import com.bupjangsa.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @Builder
    public static class RegisterRequest {

        private String id;
        private String password;
        private String name;

        public User toEntity(String password){
            return User.builder()
                    .userId(id)
                    .password(password)
                    .userName(name)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UpdateRequest {

        private String name;

    }



}
