package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;

public interface SeekPersister {
    AllBoard selectBoard(String boardType, int boardNo);
}
