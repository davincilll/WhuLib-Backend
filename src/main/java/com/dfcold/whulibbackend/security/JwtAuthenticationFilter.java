package com.dfcold.whulibbackend.security;

import com.dfcold.whulibbackend.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dfcold
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("No JWT token found in request headers");
        }
        // 去掉 "Bearer " 前缀
        token = token.substring(7);
        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(null, token);
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}