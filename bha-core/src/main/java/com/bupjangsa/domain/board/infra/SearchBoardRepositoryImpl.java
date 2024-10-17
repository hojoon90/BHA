package com.bupjangsa.domain.board.infra;

import com.bupjangsa.domain.board.BoardType;
import com.bupjangsa.domain.board.dto.BoardCriteria;
import com.bupjangsa.domain.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bupjangsa.domain.board.entity.QBoard.*;

@Repository
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
        implements SearchBoardRepository {

    private final JPAQueryFactory queryFactory;

    public SearchBoardRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Board.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Board> selectArticlePage(BoardCriteria.SearchList criteria,
                                         Pageable pageable) {
        final JPQLQuery<Board> query = queryFactory.selectFrom(board)
                .where(
                        boardTypeContains(criteria.getBoardType())
                )
                .orderBy(board.postId.desc());

        final long total_count = query.fetch().size();
        final List<Board> boardList = getQuerydsl()
                .applyPagination(pageable, query).fetch();

        return new PageImpl<>(boardList, pageable, total_count);
    }

    private BooleanExpression boardTypeContains(final BoardType boardType) {
        return boardType != null ? board.boardType.eq(boardType)
                : null;
    }

}
