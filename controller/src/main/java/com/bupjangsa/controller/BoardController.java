package com.bupjangsa.controller;

import com.bupjangsa.domain.AllBoard;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/board/*")
public class BoardController {

    @PostMapping(value = "/article")
    public void postArticle(){
        //TODO 게시글 등록
    }

    @GetMapping(value = "/article")
    public AllBoard getArticle(){
        //TODO 게시글 조회
        return null;
    }

    @PutMapping(value = "/article")
    public void putArticle(){
        //TODO 게시글 수정
    }


}
