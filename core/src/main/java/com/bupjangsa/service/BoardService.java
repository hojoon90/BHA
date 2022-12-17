package com.bupjangsa.service;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.repository.CollectPersister;
import com.bupjangsa.repository.SeekPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final SeekPersister seekPersister;
    private final CollectPersister collectPersister;

    public void postArticle(AllBoard allBoard){
        collectPersister.postArticle(allBoard);
    }

    public AllBoard selectBoard(String boardType, int boardNo){
        return seekPersister.selectBoard(boardType, boardNo);
    }

    public void putArticle(AllBoard allBoard){
        collectPersister.putArticle(allBoard);
    }
}
