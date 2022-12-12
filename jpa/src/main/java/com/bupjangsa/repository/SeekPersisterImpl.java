package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.domain.QAllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SeekPersisterImpl implements SeekPersister{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Override
    public AllBoard selectBoard() {
        QAllBoard qallBoard = new QAllBoard("allBoard");

        return jpaQueryFactory.select(qallBoard).from(qallBoard)
                .fetchOne();
    }
}
