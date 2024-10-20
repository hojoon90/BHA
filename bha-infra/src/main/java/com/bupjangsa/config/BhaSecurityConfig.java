package com.bupjangsa.config;

import com.bupjangsa.message.MessageConst;
import com.bupjangsa.security.dto.AuthErrorResponse;
import com.bupjangsa.security.filter.JwtAuthenticationFilter;
import com.bupjangsa.security.service.BhaSecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

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

        //https://non-stop.tistory.com/667
        //https://velog.io/@park2348190/Spring-Security%EC%9D%98-Unauthorized-Forbidden-%EC%B2%98%EB%A6%AC
        http
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(unauthorizedEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(
                        config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        registry ->
                                registry
                                        .requestMatchers(
                                                AntPathRequestMatcher.antMatcher("/"),
                                                AntPathRequestMatcher.antMatcher("/docs/**"),
                                                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                                                AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                                                AntPathRequestMatcher.antMatcher("/h2-console/**"),

                                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/user"),
                                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/user/login"),

                                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/post/**")

                                        ).permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
        ;
        http.addFilterAfter(jwtAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    //인가 처리
    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, authException) -> {
                AuthErrorResponse<Void> errorResponse = AuthErrorResponse.<Void>builder()
                        .resultCode(HttpStatus.FORBIDDEN.value())
                        .resultMsg(MessageConst.FORBIDDEN_AUTHORIZED.getMessage())
                        .build();

                sendResponse(response, errorResponse);
            };

    //인증 처리
    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                AuthErrorResponse<Void> errorResponse = AuthErrorResponse.<Void>builder()
                        .resultCode(HttpStatus.UNAUTHORIZED.value())
                        .resultMsg(MessageConst.UNAUTHORIZED_TOKEN.getMessage())
                        .build();

                sendResponse(response, errorResponse);
            };

    private void sendResponse(HttpServletResponse response, AuthErrorResponse<Void> errorResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonErrorResponse = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(errorResponse.getResultCode());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // application/json
        response.getWriter().write(jsonErrorResponse);
    }

}
