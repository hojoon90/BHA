package com.bupjangsa.repository;

import com.bupjangsa.domain.AllBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CollectPersisterImpl implements CollectPersister{


    JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager entityManager;

    public CollectPersisterImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager){
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }


    @Override
    public void postArticle(AllBoard allBoard) {
        //queryDsl은 insert시엔 EntityManager를 사용한다.
        entityManager.persist(allBoard);
    }

    @Override
    public void putArticle(AllBoard allBoard) {

    }
}
