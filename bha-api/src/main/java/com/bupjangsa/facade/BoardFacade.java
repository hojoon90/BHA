package com.bupjangsa.facade;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.domain.board.type.BoardType;
import com.bupjangsa.domain.board.dto.BoardCriteria;
import com.bupjangsa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.bupjangsa.domain.board.dto.BoardDto.*;
import static com.bupjangsa.dto.request.BoardRequest.*;
import static com.bupjangsa.dto.response.BoardResponse.*;

/**
 * Facade 는 아래 역할만 수행한다.
 *
 * DTO -> Entity 변환처리
 * Entity -> DTO 변환처리
 */
@Service
@RequiredArgsConstructor
public class BoardFacade {

    private final BoardService boardService;

    /**
     * 게시물 등록
     * @param userId
     * @param request
     * @return
     */
    public AppResponse<Void> postArticle(Long userId, PostRegisterRequest request){

        Register register = Register.builder()
                .title(request.getTitle())
                .boardType(request.getBoardType())
                .contents(request.getContent())
                .userId(userId)
                .build();

        boardService.postArticle(register);
        return AppResponse.responseVoidSuccess(HttpStatus.CREATED.value());
    }

    /**
     * 게시물 업데이트
     * @param userId
     * @param request
     * @return
     */
    public AppResponse<Void> updateArticle(Long userId, PostUpdateRequest request){

        Update update = Update.builder()
                .postNo(request.getPostNo())
                .title(request.getTitle())
                .contents(request.getContent())
                .boardType(request.getBoardType())
                .userId(userId)
                .build();

        boardService.updateArticle(update);
        return AppResponse.responseVoidSuccess(HttpStatus.NO_CONTENT.value());
    }

    /**
     * 게시물 삭제
     * @param userId
     * @param request
     * @return
     */
    public AppResponse<Void> deleteArticle(Long userId, PostDeleteRequest request){

        Delete delete = Delete.builder()
                .postNo(request.getPostNo())
                .boardType(request.getBoardType())
                .userId(userId)
                .build();

        boardService.deleteArticle(delete);
        return AppResponse.responseVoidSuccess(HttpStatus.NO_CONTENT.value());
    }

    /**
     * 게시물 조회
     * @param boardTypeStr
     * @param postNo
     * @return
     */
    public AppResponse<PostDetail> selectArticle(String boardTypeStr, Long postNo){

        final BoardType boardType = BoardType.valueOf(boardTypeStr);
        PostInfo postInfo = boardService.selectArticle(boardType, postNo);

        return AppResponse.responseSuccess(PostDetail.from(postInfo));
    }

    /**
     * 게시판 리스트 조회
     * @param request
     * @return
     */
    public AppResponse<PostPage> selectArticleList(PageablePostSearchRequest request){
        BoardCriteria.SearchList criteria = BoardCriteria.SearchList.builder()
                .boardType(BoardType.valueOf(request.getBoardType()))
                .build();
        Page<PostInfo> postInfos = boardService.selectArticleList(criteria, request.getPageRequest());

        PostPage page = PostPage.of(postInfos.getTotalElements(), postInfos.getTotalPages()
                , postInfos.getPageable().getPageSize(), postInfos.getContent());
        return AppResponse.responseSuccess(page);
    }



}
