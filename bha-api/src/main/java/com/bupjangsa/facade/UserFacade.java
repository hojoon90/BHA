package com.bupjangsa.facade;

import com.bupjangsa.service.SecurityService;
import com.bupjangsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
