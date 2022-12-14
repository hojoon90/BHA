package com.bupjangsa.service;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.repository.CollectPersister;
import com.bupjangsa.repository.SeekPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final SeekPersister seekPersister;
    private final CollectPersister collectPersister;

    public void postArticle(AllBoard allBoard){
        collectPersister.postArticle(allBoard);
    }

    public AllBoard selectBoard(String boardType, int boardNo){
        return seekPersister.selectBoard(boardType, boardNo);
    }
}
