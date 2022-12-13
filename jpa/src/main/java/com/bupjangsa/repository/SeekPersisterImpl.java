package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.domain.QAllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

public class SeekPersisterImpl implements SeekPersister{

    JPAQueryFactory jpaQueryFactory;

    public SeekPersisterImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public AllBoard selectBoard(String boardType, int boardNo) {
        QAllBoard qallBoard = new QAllBoard("allBoard");

        return jpaQueryFactory.select(qallBoard).from(qallBoard)
                .where(qallBoard.boardType.eq(boardType))
                .where(qallBoard.no.eq(boardNo))
                .fetchOne();
    }
}
