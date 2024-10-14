package com.bupjangsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BHAApplication {
    public static void main(String[] args){
        System.setProperty("spring.config.name", "application,jpa-config");
        SpringApplication.run(BHAApplication.class, args);
    }
}
