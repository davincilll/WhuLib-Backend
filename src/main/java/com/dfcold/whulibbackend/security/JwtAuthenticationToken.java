package com.dfcold.whulibbackend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author dfcold
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private String credentials;
    public JwtAuthenticationToken(Object principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        // 该令牌刚创建时未认证
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}