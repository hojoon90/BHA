package com.bupjangsa.facade;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.domain.user.dto.UserDto;
import com.bupjangsa.dto.JwtDto;
import com.bupjangsa.exception.AuthorizeException;
import com.bupjangsa.service.SecurityService;
import com.bupjangsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bupjangsa.dto.request.UserRequest.*;
import static com.bupjangsa.dto.response.UserResponse.*;

/**
 * Facade 는 아래 역할만 수행한다.
 *
 * DTO -> Entity 변환처리
 * Entity -> DTO 변환처리
 */
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final SecurityService securityService;

    /**
     * 회원 가입 메서드
     * TODO 이메일 인증 방식 필요
     * @param request
     * @return
     */
    @Transactional
    public AppResponse<Void> registUser(RegisterRequest request){
        String encodePassword = securityService.encodePassword(request.getPassword());

        UserDto.Register dto = UserDto.Register.builder()
                .accountId(request.getAccountId())
                .password(encodePassword)
                .userName(request.getName())
                .build();

        userService.registerUser(dto);
        return AppResponse.responseVoidSuccess(HttpStatus.CREATED.value());
    }

    /**
     * 회원 정보 조회 메서드
     * @param id
     * @return
     */
    public AppResponse<UserInfo> findUser(Long userId){
        UserDto.UserInfo user = userService.findUser(userId);

        UserInfo response = UserInfo.builder()
                .accountId(user.getAccountId())
                .userName(user.getUserName())
                .build();

        return AppResponse.responseSuccess(response);
    }


    /**
     * 회원 정보 수정 메서드
     * @param userId
     * @param request
     * @return
     */
    @Transactional
    public AppResponse<Void> updateUser(Long userId, UpdateRequest request) {

        UserDto.Update dto = UserDto.Update.builder()
                .id(userId)
                .name(request.getName())
                .build();

        userService.updateUser(dto);
        return AppResponse.responseVoidSuccess(HttpStatus.NO_CONTENT.value());
    }

    /**
     * 회원 탈퇴 메서드
     * @param id
     * @return
     */
    public AppResponse<Void> deleteUser(Long userId){
        userService.deleteUser(userId);
        return AppResponse.responseVoidSuccess(HttpStatus.NO_CONTENT.value());
    }

    /**
     * 로그인 처리
     * @param request
     * @return
     */
    public AppResponse<TokenResponse> login(LoginRequest request){

        UserDto.UserInfo userByUserId = userService.findUserByAccountId(request.getAccountId());
        boolean checkPassword = securityService.checkUserValidation(request.getPassword(), userByUserId.getPassword());
        if(!checkPassword) throw new AuthorizeException("패스워드가 맞지 않습니다.");

        JwtDto.Tokens tokens = securityService.getTokens(userByUserId.getUserId(), userByUserId.getAccountId());
        TokenResponse response = TokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .build();

        return AppResponse.responseSuccess(response);
    }

}
