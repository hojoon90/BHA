package com.bupjangsa.controller;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.domain.ResultMap;
import com.bupjangsa.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/board/*")
public class BoardController {

    private final BoardService boardService;
    ResultMap resultMap;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping(value = "/article")
    public ResultMap postArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        boardService.postArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @PutMapping(value = "/article")
    public ResultMap putArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        boardService.putArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @DeleteMapping(value = "/article")
    public ResultMap deleteArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        // 게시물 삭제이지만 DB 상에서는 deleteYn 값을 Y로만 변경해준다.
        boardService.deleteArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @GetMapping(value = "/article/{boardType}/{boardNo}")
    public AllBoard getArticle(@PathVariable String boardType, @PathVariable int boardNo){
        return boardService.selectArticle(boardType, boardNo);
    }

    @GetMapping(value = "/article/{boardType}")
    public List<AllBoard> getArticleList(@PathVariable String boardType, @RequestParam Map<String, String> param){

        int pageNo = Integer.parseInt(param.get("pageNo"));
        int pageSize = Integer.parseInt(param.get("pageSize"));

        return boardService.selectArticleList(boardType, pageNo, pageSize);
    }
}
