package com.bupjangsa.controller;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.security.dto.AppUserDetails;
import com.bupjangsa.dto.response.BoardResponse.PostDetail;
import com.bupjangsa.facade.BoardFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.bupjangsa.dto.request.BoardRequest.*;
import static com.bupjangsa.dto.response.BoardResponse.PostPage;

@RestController
@RequestMapping(value = "api/v1/board/post")
@RequiredArgsConstructor
public class PostConstoller {

    private final BoardFacade boardFacade;

    @PostMapping
    public ResponseEntity<AppResponse<Void>> registerPost(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody final PostRegisterRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardFacade.registerPost(user.getUserId(), request));
    }

    @PutMapping
    public ResponseEntity<AppResponse<Void>> updatePost(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody PostUpdateRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.updatePost(user.getUserId(), request));
    }

    @DeleteMapping
    public ResponseEntity<AppResponse<Void>> deletePost(
            @AuthenticationPrincipal AppUserDetails user,
            @RequestBody PostDeleteRequest request
    ){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(boardFacade.deletePost(user.getUserId(), request));
    }

    @GetMapping(value = "/{boardType}/{postNo}")
    public ResponseEntity<AppResponse<PostDetail>> getPost(
            @PathVariable String boardType,
            @PathVariable Long postNo
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.selectPost(boardType, postNo));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<AppResponse<PostPage>> getPostList(
            @Valid final PageablePostSearchRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardFacade.selectPostList(request));
    }
}
