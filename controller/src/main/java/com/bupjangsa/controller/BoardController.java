package com.bupjangsa.controller;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/board/*")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping(value = "/article")
    public void postArticle(@RequestBody AllBoard allBoard){
        boardService.postArticle(allBoard);
    }

    @PutMapping(value = "/article")
    public void putArticle(@RequestBody AllBoard allBoard){
        boardService.putArticle(allBoard);
    }

    @DeleteMapping(value = "/article")
    public void deleteArticle(@RequestBody AllBoard allBoard){
        // 게시물 삭제이지만 DB 상에서는 deleteYn 값을 Y로만 변경해준다.
        boardService.deleteArticle(allBoard);
    }

    @GetMapping(value = "/article/{boardType}/{boardNo}")
    public AllBoard getArticle(@PathVariable String boardType, @PathVariable int boardNo){
        return boardService.selectArticle(boardType, boardNo);
    }

    @GetMapping(value = "/article/{boardType}")
    public List<AllBoard> getArticleList(@PathVariable String boardType){
        return boardService.selectArticleList(boardType);
    }
}
