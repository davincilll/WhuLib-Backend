package com.dfcold.whulibbackend.domain.service.impl;

import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.mapper.UserMapper;
import com.dfcold.whulibbackend.domain.service.IUserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author dfcold
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
