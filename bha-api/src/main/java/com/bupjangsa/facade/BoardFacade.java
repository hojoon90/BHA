package com.bupjangsa.facade;

import com.bupjangsa.common.AppResponse;
import com.bupjangsa.domain.board.BoardType;
import com.bupjangsa.dto.request.BoardRequest;
import com.bupjangsa.dto.response.BoardResponse;
import com.bupjangsa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AppResponse<Void> postArticle(Long userId, PostRegisterRequest request){


        return AppResponse.responseSuccess(null);
    }
    public AppResponse<Void> updateArticle(Long userId, PostUpdateRequest request){


        return AppResponse.responseSuccess(null);
    }
    public AppResponse<Void> deleteArticle(PostDeleteRequest request){


        return AppResponse.responseSuccess(null);
    }
    public AppResponse<PostInfo> selectArticle(String boardType, Long id){


        return AppResponse.responseSuccess(null);
    }
    public AppResponse<List<PostInfo>> selectArticleList(String boardType){


        return AppResponse.responseSuccess(null);
    }



}
