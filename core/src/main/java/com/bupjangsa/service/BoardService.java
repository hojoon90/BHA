package com.bupjangsa.service;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.repository.CollectPersister;
import com.bupjangsa.repository.SeekPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final SeekPersister seekPersister;
    private final CollectPersister collectPersister;

    @Transactional  //Transaction은 비즈니스가 시작하는 위치에 놓는게 제일 좋다. 자세한 내용은 정리 후 올릴 예정.
    public void postArticle(AllBoard allBoard){
        collectPersister.postArticle(allBoard);
    }


    @Transactional
    public void putArticle(AllBoard allBoard){
        collectPersister.putArticle(allBoard);
    }

    @Transactional
    public void deleteArticle(AllBoard allBoard){
        collectPersister.deleteArticle(allBoard);
    }

    //단건 조회
    public AllBoard selectArticle(String boardType, int boardNo){
        return seekPersister.selectArticle(boardType, boardNo);
    }

    //게시물 목록 조회
    public List<AllBoard> selectArticleList(String boardType, int pageNo, int pageSize){
        // 리스트 페이징. TODO 게시물이 많아질때 처리 고민 필요.
        List<AllBoard> boardList = seekPersister.selectArticleList(boardType);

        return boardList.stream()
                .skip(pageSize * (pageNo -1))
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}
