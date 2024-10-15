package com.bupjangsa.controller;

import com.bupjangsa.common.CommonResponse;
import com.bupjangsa.domain.user.User;
import com.bupjangsa.dto.UserDto;
import com.bupjangsa.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    //사용자 가입
    @PostMapping
    public ResponseEntity<CommonResponse> registUser(@RequestBody final UserDto.RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userFacade.registUser(request));
    }

    @GetMapping(value = "/login")
    public ResponseEntity<CommonResponse<User>> getUser(@PathVariable Long userNo){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userFacade.selectUserInfo(userNo));
    }


    //회원정보 수정
    @PutMapping
    public ResponseEntity<CommonResponse> updateUser(@RequestBody final UserDto.UpdateRequest request){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(userFacade.updateUser(0L, request));
    }

    //회원 탈퇴
    @DeleteMapping
    public ResponseEntity<CommonResponse> deleteUser(@RequestBody User user){
        //회원탈퇴의 경우 탈퇴여부값만 변경해준다.
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(userFacade.updateUser(user));
    }

    //회원 조회
    //TODO 필요한 회원 정보 정의 필요.
    @GetMapping(value = "/{userNo}")
    public ResponseEntity<CommonResponse<User>> getUser(@PathVariable Long userNo){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userFacade.selectUserInfo(userNo));
    }

}
