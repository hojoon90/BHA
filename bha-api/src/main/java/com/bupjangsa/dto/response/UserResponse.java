package com.bupjangsa.dto.response;

import lombok.Builder;
import lombok.Getter;

public class UserResponse {

    private UserResponse(){
        /*Do Nothing*/
    }

    @Getter
    @Builder
    public static class TokenResponse {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Builder
    public static class UserInfo{
        private String accountId;
        private String userName;
    }

}
