package com.bupjangsa.security.dto;

import lombok.Builder;
import lombok.Getter;

public class JwtDto {

    @Getter
    @Builder
    public static class Tokens{

        private String accessToken;
        private String refreshToken;

    }

}
