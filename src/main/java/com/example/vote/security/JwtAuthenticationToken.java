package com.example.vote.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;

    public JwtAuthenticationToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // 인증 상태로 설정
    }

    @Override
    public Object getCredentials() {
        return null; // JWT에서는 비밀번호가 필요 없으므로 null 반환
    }

    @Override
    public Object getPrincipal() {
        return principal; // 인증된 사용자 반환
    }
}
