package com.bupjangsa.controller;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.service.BoardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/board/*")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping(value = "/article")
    public void postArticle(@RequestBody AllBoard allBoard){
//        boardService
        //TODO 게시글 등록
    }

    @GetMapping(value = "/article/{boardType}/{boardNo}")
    public AllBoard getArticle(@PathVariable String boardType, @PathVariable int boardNo){
        //TODO 게시글 조회
        return boardService.selectBoard(boardType, boardNo);
    }

    @PutMapping(value = "/article")
    public void putArticle(){
        //TODO 게시글 수정
    }


}
