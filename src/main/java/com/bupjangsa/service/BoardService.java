package com.bupjangsa.service;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.repository.BoardPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardPersister boardPersister;

    @Transactional
    public void postArticle(AllBoard allBoard){
        boardPersister.postArticle(allBoard);
    }


    @Transactional
    public void putArticle(AllBoard allBoard){
        boardPersister.putArticle(allBoard);
    }

    @Transactional
    public void deleteArticle(AllBoard allBoard){
        boardPersister.deleteArticle(allBoard);
    }

    //단건 조회
    public AllBoard selectArticle(String boardType, int boardNo){
        return boardPersister.selectArticle(boardType, boardNo);
    }

    //게시물 목록 조회
    public List<AllBoard> selectArticleList(String boardType, int pageNo, int pageSize){
        // 리스트 페이징. TODO 게시물이 많아질때 처리 고민 필요.
        List<AllBoard> boardList = boardPersister.selectArticleList(boardType);

        return boardList.stream()
                .skip(pageSize * (pageNo -1))
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
