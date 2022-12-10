package com.mco.seek;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

//https://gaebal-goebal.tistory.com/49
//https://velog.io/@lehdqlsl/SpringBoot-JPA-Multiple-Databases-%EC%84%A4%EC%A0%95
//Datasource 설정 확인할 것
@Slf4j
@Getter
@EnableTransactionManagement
public class DataSourceFactoryConfig {
    private DSLContext dslContext ;
    private String pathInfo;

    public DataSourceFactoryConfig(String pathInfo) {
        this.pathInfo = "hikari/" + pathInfo + "-config.properties";
        this.dslContext = getSessionFactory();
    }
    public DSLContext getSessionFactory() {
        try {
            HikariConfig config = new HikariConfig(Resources.getResourceAsProperties(pathInfo));
            HikariDataSource dataSouce = new HikariDataSource(config);
            return DSL.using(dataSouce, SQLDialect.MYSQL);
        }catch (IOException var4) {
        }
        return null;
    }


}
