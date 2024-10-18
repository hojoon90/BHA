package com.bupjangsa.security.filter;

import com.bupjangsa.security.service.BhaSecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String[] NO_CHECK_URL = {"/api/v1/user/login", "/api/v1/user/register", "/h2-console"}; // "/login"으로 들어오는 요청은 Filter 작동 X

    private final BhaSecurityService bhaSecurityService;
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) {
        try{
            log.debug("checkAccessTokenAndAuthentication() 호출");

            bhaSecurityService.extractAccessToken(request)
                    .filter(bhaSecurityService::isTokenValid)
                    .flatMap(bhaSecurityService::getUserDetails)
                    .ifPresent(this::saveAuthentication);

            filterChain.doFilter(request, response);
        }catch (Exception e){
            jwtExceptionHandler(response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(NO_CHECK_URL)
                .anyMatch(request.getRequestURI()::contains);
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
