package com.bupjangsa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    //패스워드 인코딩을 위한 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() { return  new BCryptPasswordEncoder(); }

}
