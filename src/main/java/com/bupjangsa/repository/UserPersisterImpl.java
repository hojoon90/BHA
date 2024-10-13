package com.bupjangsa.repository;

import com.bupjangsa.domain.QUserInfo;
import com.bupjangsa.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

        entityManager.persist(userInfo);
        entityManager.flush();
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        QUserInfo qUserInfo = new QUserInfo("userInfo");

        jpaQueryFactory.update(qUserInfo)
                .where(qUserInfo.userId.eq(userInfo.getUserId()))
                .set(qUserInfo.secretKey, userInfo.getSecretKey())
                .set(qUserInfo.email, userInfo.getEmail())
                .set(qUserInfo.altDate, date)
                .execute();
    }

    @Override
    public UserInfo getUser(String userId) {
        QUserInfo qUserInfo = new QUserInfo("userInfo");

        return jpaQueryFactory.select(qUserInfo)
                .from(qUserInfo)
                .where(qUserInfo.userId.eq(userId))
                .fetchOne();
    }
}
