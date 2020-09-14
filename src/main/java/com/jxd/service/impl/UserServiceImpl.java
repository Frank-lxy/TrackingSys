package com.jxd.service.impl;

import com.jxd.dao.IUserDao;
import com.jxd.model.User;
import com.jxd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/11 23:13
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }
}
