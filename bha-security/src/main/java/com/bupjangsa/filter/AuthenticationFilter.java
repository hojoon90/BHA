package com.bupjangsa.filter;

import com.bupjangsa.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> NO_CHECK_URL =
            Arrays.asList("/user", "/h2-console", "/swagger-ui", "/swagger-config", "/docs");

    private final SecurityService securityService;

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) {
        try{
            boolean isPassed = NO_CHECK_URL.stream().anyMatch(request.getRequestURI()::contains);
            if(isPassed){
                filterChain.doFilter(request, response);
                return;
            }
            log.debug("checkAccessTokenAndAuthentication() 호출");

            securityService.extractAccessToken(request)
                    .filter(securityService::isTokenValid)
                    .flatMap(securityService::getUserDetails)
                    .ifPresent(this::saveAuthentication);

            filterChain.doFilter(request, response);
        }catch (Exception e){
            jwtExceptionHandler(response);
        }
    }

    public void saveAuthentication(UserDetails userDetailsUser) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void jwtExceptionHandler(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString("사용할 수 없는 토큰입니다.");
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
