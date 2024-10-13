package com.bupjangsa.config;

import com.bupjangsa.repository.BoardPersisterImpl;
import com.bupjangsa.repository.UserPersisterImpl;
import com.bupjangsa.service.BoardService;
import com.bupjangsa.service.UserService;
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
        return new BoardService(new BoardPersisterImpl(jpaQueryFactory(), em));
    }

    @Bean
    public UserService userService(){
        return new UserService(new UserPersisterImpl(jpaQueryFactory(), em));
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
