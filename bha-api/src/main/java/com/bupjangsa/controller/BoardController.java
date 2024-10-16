package com.bupjangsa.controller;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.dto.AppUserDetails;
import com.bupjangsa.dto.response.BoardResponse.PostInfo;
import com.bupjangsa.facade.BoardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bupjangsa.dto.request.BoardRequest.*;

@RestController
@RequestMapping(value = "api/v1/board/article")
@RequiredArgsConstructor
public class BoardController {

    private final BoardFacade boardFacade;

    @PostMapping
    public ResponseEntity<AppResponse<Void>> postArticle(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody final PostRegisterRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardFacade.postArticle(user.getUserId(), request));
    }

    @PutMapping
    public ResponseEntity<AppResponse<Void>> putArticle(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody PostUpdateRequest request
    ){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(boardFacade.updateArticle(user.getUserId(), request));
    }

    @DeleteMapping
    public ResponseEntity<AppResponse<Void>> deleteArticle(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody PostDeleteRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.deleteArticle(request));
    }

    @GetMapping(value = "/{boardType}/{boardNo}")
    public ResponseEntity<AppResponse<PostInfo>>  getArticle(@PathVariable String boardType,
                                                             @PathVariable Long boardNo){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.selectArticle(boardType, boardNo));
    }

    @GetMapping(value = "/{boardType}")
    public ResponseEntity<AppResponse<List<PostInfo>>>  getArticleList(
            @PathVariable String boardType
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.selectArticleList(boardType));
    }
}
