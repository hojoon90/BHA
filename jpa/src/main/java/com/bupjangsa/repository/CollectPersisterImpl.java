package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CollectPersisterImpl implements CollectPersister{

    JPAQueryFactory jpaQueryFactory;

    public CollectPersisterImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public void postArticle(AllBoard allBoard) {

    }
}
