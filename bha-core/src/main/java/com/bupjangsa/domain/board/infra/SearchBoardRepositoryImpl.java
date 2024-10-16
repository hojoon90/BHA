package com.bupjangsa.domain.board.infra;

import com.bupjangsa.domain.board.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchBoardRepositoryImpl implements SearchBoardRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Board selectArticle(String boardType, int boardNo) {
        return null;
    }

    @Override
    public List<Board> selectArticleList(String boardType) {
        return null;
    }


}
