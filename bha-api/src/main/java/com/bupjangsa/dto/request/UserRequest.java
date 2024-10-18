package com.bupjangsa.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

    @Getter
    @Builder
    public static class RegisterRequest {

        private String accountId;
        private String password;
        private String name;

    }

    @Getter
    @Builder
    public static class LoginRequest{
        private String accountId;
        private String password;
    }

    @Getter
    @Builder
    public static class UpdateRequest {

        private String name;

    }



}
