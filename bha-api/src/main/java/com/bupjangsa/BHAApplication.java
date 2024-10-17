package com.bupjangsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BHAApplication {
    public static void main(String[] args){
        System.setProperty("spring.config.name", "application,jpa-config,security-config");
        SpringApplication.run(BHAApplication.class, args);
    }
}
