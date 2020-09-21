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

    /**
     * 通过userName和权限进行查询
     * @param userName
     * @param role
     * @return
     */
    List<User> getUsers(@Param("userName")String userName,@Param("role")Integer role);

    /**
     * 查询出过滤后的数据并分页
     * @param userName
     * @param role
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<User> getUsersByPage(@Param("userName")String userName,@Param("role")Integer role,@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    /**
     * 获取全部的用户
     * @return
     */
    List<User> getAllUser();

    List<User> getUsers();

    /**
     * 获取全部的教师
     * @return
     */
    List<User> getAllTeachers();

    /**
     * 获取最大的用户Id
     * @return
     */
    List<User>getMaxUserId();

    /**
     * 编辑用户密码
     * @param user
     * @return
     */
    boolean editPwdById(User user);

    /**
     * 通过userId获取user数据
     * @param userId
     * @return
     */
    List<User> getUserById(Integer userId);

    /**
     * 通过userId进行删除
     * @param userId
     * @return
     */
    boolean deleteById(Integer userId);

    /**
     * 获取全部信息分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<User> getAllUserByPage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    /**
     * 更新管理员
     * @param userId
     * @param password
     * @return
     */
    boolean updateAdmin(@Param("userId")Integer userId, @Param("password")String password);

    /**
     * 更新用户信息
     * @param userId
     * @param password
     * @return
     */
    boolean updateUserPwd(@Param("userId")Integer userId, @Param("password")String password);
    boolean updateUserName(@Param("userName")String userName , @Param("userId")Integer userId);
    /**
     * 新增用户信息
     * @param userName
     * @param password
     * @param role
     * @return
     */
    boolean addUser(@Param("userName")String userName, @Param("password")String password, @Param("role")Integer role);
}
