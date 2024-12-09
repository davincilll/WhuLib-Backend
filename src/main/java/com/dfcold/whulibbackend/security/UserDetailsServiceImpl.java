package com.dfcold.whulibbackend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author dfcold
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 实现根据用户名加载用户的逻辑
        // 这里可以从数据库中加载用户
        // 返回 UserDetails 实例
        return null;
    }
}