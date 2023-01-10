package com.bupjangsa.security;

import com.querydsl.core.annotations.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Config
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //패스워드 인코딩을 위한 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() { return  new BCryptPasswordEncoder(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //CSRF 설정 Disable
        http.csrf().disable();
    }
}
