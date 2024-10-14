package com.bupjangsa.domain.board;

import java.util.List;

public interface SearchBoardRepository {
    AllBoard selectArticle(String boardType, int boardNo);
    List<AllBoard> selectArticleList(String boardType);
    void putArticle(AllBoard allBoard);
    void deleteArticle(AllBoard allBoard);
}
