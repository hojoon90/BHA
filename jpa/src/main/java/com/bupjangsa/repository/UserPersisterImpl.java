package com.bupjangsa.repository;

import com.bupjangsa.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

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

    }
}
