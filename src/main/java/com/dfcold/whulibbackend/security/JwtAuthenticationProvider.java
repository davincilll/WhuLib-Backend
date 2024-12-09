package com.dfcold.whulibbackend.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        // 验证 JWT（这里可以使用 JWT 库解析和验证）
        // 自定义方法
        String username = validateTokenAndGetUsername(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new JwtAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    private String validateTokenAndGetUsername(String token) {
        // 实现 JWT 验证逻辑，返回用户名
        // 这里只是示例
        return "username";
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}