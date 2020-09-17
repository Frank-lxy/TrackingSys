package com.jxd.dao;

import com.jxd.model.User;

public interface IUserDao {
    /**
     * 获得登录用户
     * @param user 登录用户
     * @return 用户
     */
    User getUser(User user);

    /**
     * 修改用户密码
     * @param user 用户
     * @return 是否修改成功
     */
    boolean editPwdById(User user);
}
