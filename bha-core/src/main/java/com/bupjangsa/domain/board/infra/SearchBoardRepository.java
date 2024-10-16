package com.bupjangsa.domain.board.infra;

import com.bupjangsa.domain.board.entity.Board;

import java.util.List;

public interface SearchBoardRepository {
    Board selectArticle(String boardType, int boardNo);
    List<Board> selectArticleList(String boardType);
}
