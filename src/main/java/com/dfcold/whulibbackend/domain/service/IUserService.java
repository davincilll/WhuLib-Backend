package com.dfcold.whulibbackend.domain.service;

import com.dfcold.whulibbackend.domain.entity.User;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;

/**
 * @author dfcold
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名密码获取用户
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    default User getUserByUsernameAndPassWord(String username, String password){
        QueryWrapper queryWrapper = QueryWrapper.create();
                queryWrapper
                .from(User.class)
                .where(User::getUsername).eq(username)
                .and(User::getPassword).eq(password);
        return this.getOne(queryWrapper);
    }
}
