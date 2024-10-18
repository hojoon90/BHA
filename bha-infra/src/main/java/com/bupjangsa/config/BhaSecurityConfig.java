package com.bupjangsa.config;

import com.bupjangsa.security.filter.JwtAuthenticationFilter;
import com.bupjangsa.security.service.BhaSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class BhaSecurityConfig {

    private final BhaSecurityService bhaSecurityService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(bhaSecurityService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(
                        config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        registry ->
                                registry
                                        .requestMatchers(
                                                AntPathRequestMatcher.antMatcher("/docs/**"),
                                                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                                                AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                                                AntPathRequestMatcher.antMatcher("/"),
                                                AntPathRequestMatcher.antMatcher("/api/v1/user/**"),
                                                AntPathRequestMatcher.antMatcher("/h2-console/**")
                                        ).permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
        ;
        http.addFilterAfter(jwtAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
