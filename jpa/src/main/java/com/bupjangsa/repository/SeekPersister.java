package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;

import java.util.List;

public interface SeekPersister {
    AllBoard selectArticle(String boardType, int boardNo);
    List<AllBoard> selectArticleList(String boardType);
}
