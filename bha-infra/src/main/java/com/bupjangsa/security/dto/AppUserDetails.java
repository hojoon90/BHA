package com.bupjangsa.security.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
public class AppUserDetails implements UserDetails {

    @Getter
    private final Long userId;
    private final String accountId;
    private final String userName;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String idType;

    public static AppUserDetails valueOf(Long userId, String accountId){
        return AppUserDetails.builder()
                .userId(userId)
                .accountId(accountId)
                .build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
