package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.bupjangsa.domain.QAllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class BoardPersisterImpl implements BoardPersister {

    JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager entityManager;

    LocalDateTime localDateTime = LocalDateTime.now();

    public BoardPersisterImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager){
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public AllBoard selectArticle(String boardType, int boardNo) {
        QAllBoard qallBoard = new QAllBoard("allBoard");

        return jpaQueryFactory.select(qallBoard)
                .from(qallBoard)
                .where(qallBoard.boardType.eq(boardType))
                .where(qallBoard.no.eq(boardNo))
                .fetchOne();
    }

    @Override
    public List<AllBoard> selectArticleList(String boardType) {
        QAllBoard qAllBoard = new QAllBoard("allBoard");

        return jpaQueryFactory.select(qAllBoard)
                .from(qAllBoard)
                .where(qAllBoard.boardType.eq(boardType))
                .fetch();
    }

    @Override
    public void postArticle(AllBoard allBoard) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        allBoard.setRegDate(date);

        //queryDsl은 insert시엔 EntityManager를 사용한다.
        entityManager.persist(allBoard);
        entityManager.flush();
    }

    @Override
    public void putArticle(AllBoard allBoard) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        QAllBoard qallBoard = new QAllBoard("allBoard");

        jpaQueryFactory.update(qallBoard)
                .where(qallBoard.no.eq(allBoard.getNo()))
                .where(qallBoard.boardType.eq(allBoard.getBoardType()))
                .set(qallBoard.title, allBoard.getTitle())
                .set(qallBoard.content, allBoard.getContent())
                .set(qallBoard.altDate, date)
                .execute();
    }

    @Override
    public void deleteArticle(AllBoard allBoard) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        QAllBoard qAllBoard = new QAllBoard("allBoard");

        jpaQueryFactory.update(qAllBoard)
                .where(qAllBoard.no.eq(allBoard.getNo()))
                .where(qAllBoard.boardType.eq(allBoard.getBoardType()))
                .set(qAllBoard.deleteYn, allBoard.getDeleteYn())
                .set(qAllBoard.altDate, date)
                .execute();

    }
}
