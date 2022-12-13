package com.bupjangsa.config;

import com.bupjangsa.repository.CollectPersisterImpl;
import com.bupjangsa.repository.SeekPersisterImpl;
import com.bupjangsa.service.BoardService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class BHAConfig {

    private final EntityManager em;

    @Bean
    public BoardService boardService(){
        return new BoardService(new SeekPersisterImpl(jpaQueryFactory()), new CollectPersisterImpl(jpaQueryFactory()));
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
