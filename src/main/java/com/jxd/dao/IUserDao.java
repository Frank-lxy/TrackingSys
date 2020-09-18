package com.jxd.dao;

import com.jxd.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
    /**
     * 获得登录用户
     * @param user 登录用户
     * @return 用户
     */

    User getUser(User user);
    List<User> getUsers(@Param("userName")String userName,@Param("role")Integer role);
    List<User> getUsersByPage(@Param("userName")String userName,@Param("role")Integer role,@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);
    List<User> getAllUser();
    List<User> getUsers();
    List<User> getAllTeachers();
    List<User>getMaxUserId();
    List<User> getUserById(Integer userId);
    boolean deleteById(Integer userId);
    List<User> getAllUserByPage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);
    boolean updateAdmin(@Param("userId")Integer userId, @Param("password")String password);
    boolean updateUser(@Param("userId")Integer userId, @Param("password")String password);
    boolean addUser(@Param("userName")String userName, @Param("password")String password, @Param("role")Integer role);
}
