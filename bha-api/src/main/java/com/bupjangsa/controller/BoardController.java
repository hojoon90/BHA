package com.bupjangsa.controller;

import com.bupjangsa.common.CommonResponse;
import com.bupjangsa.domain.board.Board;
import com.bupjangsa.facade.BoardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardFacade boardFacade;

    @PostMapping(value = "/article")
    public ResponseEntity<CommonResponse> postArticle(@RequestBody final Board board){
        return ResponseEntity.status(HttpStatus.CREATED).body(boardFacade.postArticle(board));
    }

    @PutMapping(value = "/article")
    public ResponseEntity<CommonResponse> putArticle(@RequestBody Board board){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(boardFacade.putArticle(board));
    }

    @DeleteMapping(value = "/article")
    public ResponseEntity<CommonResponse> deleteArticle(@RequestBody Board board){
        return ResponseEntity.status(HttpStatus.OK).body(boardFacade.deleteArticle(board));
    }

    @GetMapping(value = "/article/{boardType}/{boardNo}")
    public ResponseEntity<CommonResponse<Board>>  getArticle(@PathVariable String boardType,
                                                             @PathVariable int boardNo){
        return ResponseEntity.status(HttpStatus.OK).body(boardFacade.selectArticle(boardType, boardNo));
    }

    @GetMapping(value = "/article/{boardType}")
    public ResponseEntity<CommonResponse<List<Board>>>  getArticleList(@PathVariable String boardType){
        return ResponseEntity.status(HttpStatus.OK).body(boardFacade.selectArticleList(boardType, pageNo, pageSize));
    }
}
