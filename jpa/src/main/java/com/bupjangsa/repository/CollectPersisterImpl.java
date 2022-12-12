package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.domain.QAllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectPersisterImpl implements CollectPersister{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Override
    public void postArticle(AllBoard allBoard) {

    }
}
