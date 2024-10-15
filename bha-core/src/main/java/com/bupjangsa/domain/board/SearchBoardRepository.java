package com.bupjangsa.domain.board;

import java.util.List;

public interface SearchBoardRepository {
    Board selectArticle(String boardType, int boardNo);
    List<Board> selectArticleList(String boardType);
}
