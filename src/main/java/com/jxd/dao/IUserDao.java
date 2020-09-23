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
     * @param userName 用户名
     * @param role 权限
     * @return
     */
    List<User> getUsers(@Param("userName")String userName,@Param("role")Integer role);

    /**
     * 查询出过滤后的数据并分页
     * @param userName 用户名
     * @param role 权限
     * @param pageIndex 每页条数
     * @param pageSize 跳过几页
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
     * @param user 用户
     * @return
     */
    boolean editPwdById(User user);

    /**
     * 通过userId获取user数据
     * @param userId 用户编号
     * @return
     */
    List<User> getUserById(Integer userId);

    /**
     * 通过userId进行删除
     * @param userId 用户编号
     * @return
     */
    boolean deleteById(Integer userId);

    /**
     * 获取全部信息分页
     * @param pageIndex 每页条数
     * @param pageSize 跳过几页
     * @return
     */
    List<User> getAllUserByPage(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    /**
     * 更新管理员
     * @param userId 用户名
     * @param password 密码
     * @return
     */
    boolean updateAdmin(@Param("userId")Integer userId, @Param("password")String password);

    /**
     * 更新用户信息
     * @param userId 用户名
     * @param password 密码
     * @return
     */
    boolean updateUserPwd(@Param("userId")Integer userId, @Param("password")String password);
    boolean updateUserName(@Param("userName")String userName , @Param("userId")Integer userId);
    /**
     * 新增用户信息
     * @param userName 用户名
     * @param password 密码
     * @param role 权限
     * @return
     */
    boolean addUser(@Param("userName")String userName, @Param("password")String password, @Param("role")Integer role);
}
