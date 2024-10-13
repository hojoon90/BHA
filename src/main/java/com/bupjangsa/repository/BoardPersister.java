package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;

import java.util.List;

public interface BoardPersister {
    AllBoard selectArticle(String boardType, int boardNo);
    List<AllBoard> selectArticleList(String boardType);

    void postArticle(AllBoard allBoard);
    void putArticle(AllBoard allBoard);
    void deleteArticle(AllBoard allBoard);
}
