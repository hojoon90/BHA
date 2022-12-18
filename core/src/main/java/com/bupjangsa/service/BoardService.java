package com.bupjangsa.service;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.repository.CollectPersister;
import com.bupjangsa.repository.SeekPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public AllBoard selectBoard(String boardType, int boardNo){
        return seekPersister.selectBoard(boardType, boardNo);
    }

    @Transactional
    public void putArticle(AllBoard allBoard){
        collectPersister.putArticle(allBoard);
    }
}
