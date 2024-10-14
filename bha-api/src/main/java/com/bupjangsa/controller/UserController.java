package com.bupjangsa.controller;

import com.bupjangsa.domain.user.UserInfo;
import com.bupjangsa.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    //사용자 가입
    @PostMapping
    public ResultMap registUser(@RequestBody UserInfo userInfo){
        resultMap = new ResultMap();

        userFacade.registUser(userInfo);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원정보 수정
    @PutMapping
    public ResultMap updateUser(@RequestBody UserInfo userInfo){
        resultMap = new ResultMap();

        userFacade.updateUser(userInfo);


        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원 탈퇴
    @DeleteMapping
    public ResultMap deleteUser(@RequestBody UserInfo userInfo){
        //회원탈퇴의 경우 탈퇴여부값만 변경해준다.
        resultMap = new ResultMap();

        userFacade.updateUser(userInfo);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원 조회
    //TODO 필요한 회원 정보 정의 필요.
    @GetMapping(value = "/{userNo}")
    public ResultMap getUser(
            @PathVariable Long userNo
//            @ApiParam(name = "userId", value = "사용자 아이디", example = "test" ) String userId
    ){
        resultMap = new ResultMap();

        userFacade.selectUserInfo(userNo);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");
        return resultMap;
    }

}
