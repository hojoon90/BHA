package com.bupjangsa.controller;

import com.bupjangsa.domain.ResultMap;
import com.bupjangsa.domain.UserInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user/*")
public class UserController {

    public ResultMap resultMap;

    //TODO SNS 가입 고민 필요.

    //사용자 가입
    @PostMapping(value = "/userInfo")
    public ResultMap registUser(){
        resultMap = new ResultMap();


        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원정보 수정
    @PutMapping(value = "/userInfo")
    public ResultMap updateUser(){
        resultMap = new ResultMap();


        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원 탈퇴
    @DeleteMapping(value = "/userInfo")
    public ResultMap deleteUser(){
        resultMap = new ResultMap();

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    //회원 조회
    //TODO 필요한 회원 정보 정의 필요.
    @GetMapping(value = "/userInfo")
    public UserInfo getUser(){
        resultMap = new ResultMap();


        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");
        return null;
    }

}