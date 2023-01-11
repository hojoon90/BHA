package com.bupjangsa.repository;

import com.bupjangsa.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UserPersisterImpl implements UserPersister{

    JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager entityManager;

    LocalDateTime localDateTime = LocalDateTime.now();

    public UserPersisterImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public void registUser(UserInfo userInfo) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        userInfo.setRegDate(date);


        //queryDsl은 insert시엔 EntityManager를 사용한다.
        entityManager.persist(userInfo);
        entityManager.flush();
    }

    @Override
    public void updateUser(UserInfo userInfo) {

    }

    @Override
    public UserInfo getUser(String userId) {
        return null;
    }
}
