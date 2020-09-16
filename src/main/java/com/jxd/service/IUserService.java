package com.jxd.service;

import com.jxd.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {
    /**
     * 获得登录用户
     * @param user 登录用户
     * @return 用户
     */
    User getUser(User user);
    boolean addUser(String userName, String password, Integer role);
    boolean updateUserPwd(Integer userId, String password);
    boolean updateAdmin(Integer userId, String password);
    boolean deleteById(Integer userId);
    List<User> getAllUserByPage( Integer pageIndex, Integer pageSize);
    List<User> getAllUser();
    List<User> getUserById(Integer userId);
    List<User> getUsers();
    List<User> getAllTeachers();
    List<User> getUsers(String userName,Integer role);
    List<User> getUsersByPage(String userName,Integer role,Integer pageIndex, Integer pageSize);
}
