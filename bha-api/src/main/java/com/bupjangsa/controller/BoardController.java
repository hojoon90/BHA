package com.bupjangsa.controller;

import com.bupjangsa.domain.board.AllBoard;
import com.bupjangsa.domain.ResultMap;
import com.bupjangsa.facade.BoardFacade;
import com.bupjangsa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardFacade boardFacade;
    ResultMap resultMap;

    @PostMapping(value = "/article")
    public ResultMap postArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        boardFacade.postArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @PutMapping(value = "/article")
    public ResultMap putArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        boardFacade.putArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @DeleteMapping(value = "/article")
    public ResultMap deleteArticle(@RequestBody AllBoard allBoard){
        resultMap = new ResultMap();
        //TODO 예외처리
        // 게시물 삭제이지만 DB 상에서는 deleteYn 값을 Y로만 변경해준다.
        boardFacade.deleteArticle(allBoard);

        resultMap.setResultCode(200);
        resultMap.setResultReason("OK");

        return resultMap;
    }

    @GetMapping(value = "/article/{boardType}/{boardNo}")
    public AllBoard getArticle(@PathVariable String boardType, @PathVariable int boardNo){
        return boardFacade.selectArticle(boardType, boardNo);
    }

    @GetMapping(value = "/article/{boardType}")
    public List<AllBoard> getArticleList(@PathVariable String boardType, @RequestParam Map<String, String> param){

        int pageNo = Integer.parseInt(param.get("pageNo"));
        int pageSize = Integer.parseInt(param.get("pageSize"));

        return boardFacade.selectArticleList(boardType, pageNo, pageSize);
    }
}
