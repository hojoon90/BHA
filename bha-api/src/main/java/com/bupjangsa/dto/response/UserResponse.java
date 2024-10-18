package com.bupjangsa.dto.response;

import com.bupjangsa.security.dto.JwtDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.bupjangsa.domain.user.dto.UserDto.UserInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    @Getter
    @Builder
    public static class TokenResponse {
        private String accessToken;
        private String refreshToken;

        public static TokenResponse from(JwtDto.Tokens dto){
            return TokenResponse.builder()
                    .accessToken(dto.getAccessToken())
                    .refreshToken(dto.getRefreshToken())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UserDetail {
        private String accountId;
        private String userName;

        public static UserDetail from(UserInfo dto){
            return UserDetail.builder()
                    .accountId(dto.getAccountId())
                    .userName(dto.getUserName())
                    .build();
        }
    }

}
