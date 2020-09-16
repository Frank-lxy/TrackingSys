package com.jxd.service.impl;

import com.jxd.dao.IUserDao;
import com.jxd.model.User;
import com.jxd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<User> getAllUserByPage( Integer pageIndex, Integer pageSize) {
        return userDao.getAllUserByPage(pageIndex,pageSize);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public List<User> getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }


    @Override
    public List<User> getAllTeachers() {
        return userDao.getAllTeachers();
    }

    @Override
    public List<User> getUsers(String userName, Integer role) {
        return userDao.getUsers(userName,role);
    }

    @Override
    public List<User> getUsersByPage(String userName, Integer role, Integer pageIndex, Integer pageSize) {
        return userDao.getUsersByPage(userName,role,pageIndex,pageSize);
    }

    @Override
    public boolean addUser(String userName, String password, Integer role) {
        return userDao.addUser(userName,password,role);
    }

    @Override
    public boolean updateUserPwd(Integer userId, String password) {
        return userDao.updateUser(userId,password);
    }
    @Override
    public boolean updateAdmin(Integer userId, String password) {
        return userDao.updateAdmin(userId,password);
    }

    @Override
    public boolean deleteById(Integer userId) {
        return userDao.deleteById(userId);
    }
}
