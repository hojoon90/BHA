package com.bupjangsa.dto.request;

import lombok.Builder;
import lombok.Getter;

public class UserRequest {
    private UserRequest(){
        /*Do Nothing*/
    }

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
