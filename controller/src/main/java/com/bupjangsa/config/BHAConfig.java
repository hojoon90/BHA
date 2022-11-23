package com.bupjangsa.config;

import com.bupjangsa.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BHAConfig {

    @Bean
    public DBConfig dbConfig() {return new DBConfig();}

    @Bean
    public BoardService boardService(){
        return new BoardService();
    }
}
