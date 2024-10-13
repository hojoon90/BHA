package com.bupjangsa.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.themoin.common.ResponseCodes;
import com.themoin.controller.dto.BaseResponse;
import com.themoin.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> NO_CHECK_URL =
            Arrays.asList("/user", "/h2-console", "/swagger-ui", "/swagger-config", "/docs");

    private final JwtTokenService jwtTokenService;

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

            jwtTokenService.extractAccessToken(request)
                    .filter(jwtTokenService::isTokenValid)
                    .flatMap(jwtTokenService::getUserDetails)
                    .ifPresent(this::saveAuthentication);

            filterChain.doFilter(request, response);
        }catch (Exception e){
            jwtExceptionHandler(response, ResponseCodes.UNAUTHORIZED_TOKEN);
        }
    }

    public void saveAuthentication(UserDetails userDetailsUser) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void jwtExceptionHandler(HttpServletResponse response, ResponseCodes error) {
        response.setStatus(error.getStatusCode().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new BaseResponse(error.getStatusCode().value(), error.getMessage()));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
