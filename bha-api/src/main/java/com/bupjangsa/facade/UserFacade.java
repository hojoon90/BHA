package com.bupjangsa.facade;

import com.bupjangsa.common.CommonResponse;
import com.bupjangsa.domain.user.User;
import com.bupjangsa.dto.UserDto;
import com.bupjangsa.service.SecurityService;
import com.bupjangsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * TODO 이메일 인증 방식 필요
     * @param request
     * @return
     */
    @Transactional
    public CommonResponse registUser(UserDto.RegisterRequest request){
        String encodePassword = securityService.encodePassword(request.getPassword());
        User entity = request.toEntity(encodePassword);
        userService.registUser(entity);
        return CommonResponse.responseSuccess(null);
    }

    @Transactional
    public CommonResponse updateUser(Long userId, UserDto.UpdateRequest request) {

        User user = userService.findUser(userId)
                .orElseThrow(() -> new RuntimeException(""));

        user.

    }


}
