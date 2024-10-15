package com.bupjangsa.domain.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.bupjangsa.domain.board.QBoard.*;

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
