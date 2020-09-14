package com.jxd.dao;

import com.jxd.model.User;

public interface IUserDao {
    /**
     * 获得登录用户
     * @param user 登录用户
     * @return 用户
     */
    User getUser(User user);
}
