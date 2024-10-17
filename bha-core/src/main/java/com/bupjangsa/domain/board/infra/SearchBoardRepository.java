package com.bupjangsa.domain.board.infra;

import com.bupjangsa.domain.board.dto.BoardCriteria;
import com.bupjangsa.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Page<Board> selectArticlePage(BoardCriteria.SearchList criteria, Pageable pageable);
}
