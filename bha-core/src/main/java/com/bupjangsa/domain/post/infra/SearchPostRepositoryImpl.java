package com.bupjangsa.domain.post.infra;

import com.bupjangsa.type.BoardType;
import com.bupjangsa.domain.post.dto.PostCriteria;
import com.bupjangsa.domain.post.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bupjangsa.domain.post.entity.QPost.*;

@Repository
public class SearchPostRepositoryImpl extends QuerydslRepositorySupport
        implements SearchPostRepository {

    private final JPAQueryFactory queryFactory;

    public SearchPostRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Post.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Post> selectPostPage(PostCriteria.SearchList criteria,
                                     Pageable pageable) {
        final JPQLQuery<Post> query = queryFactory.selectFrom(post)
                .where(
                        equalsBoardType(criteria.getBoardType())
                )
                .orderBy(post.postNo.desc());

        final long total_count = query.fetch().size();
        final List<Post> postList = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(postList, pageable, total_count);
    }

    private BooleanExpression equalsBoardType(final BoardType boardType) {
        return post.boardType.eq(boardType);
    }


}
