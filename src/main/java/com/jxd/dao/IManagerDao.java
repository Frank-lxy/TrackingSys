package com.jxd.dao;

import com.jxd.model.Department;
import com.jxd.model.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IManagerDao {
    /**
     * 获取全部的经理
     * @return
     */
    List<Manager>getAllManager();

    /**
     * 对经理的全部信息进行分页
     * @param count 跳过几页
     * @param page 每页几行
     * @return
     */
    List<Manager> getAllManagerByPage(@Param("count") Integer count, @Param("page") Integer page);

    /**
     * 通过经理名和部门名进行筛选查询
     * @param managerName 经理名
     * @param departmentName 部门名
     * @return
     */
    List<Manager>getManagers(@Param("managerName") String managerName, @Param("departmentName") String departmentName);

    /**
     * 新增经理
     * @param manager 经理
     * @return
     */
    boolean addManager(Manager manager);

    /**
     *在user表新增数据时新增一条详细信息为空的数据，
     * @param managerName 经理编号
     * @param userId 用户编号
     * @return
     */
    boolean addAManager(@Param("managerName")String managerName,@Param("userId")Integer userId);

    /**
     * 通过id删除经理信息
     * @param managerId 经理编号
     * @return
     */
    boolean delManagerById(Integer managerId);

    /**
     * 通过id获取全部的经理信息
     * @param managerId 经理编号
     * @return
     */
    List<Manager>getAllManagerById(Integer managerId);

    /**
     * 更新经理信息
     * @param manager 经理
     * @return
     */
    boolean updateManager( Manager manager);

    /**
     * 把userId插入到新增的数据中
     * @param userId 用户编号
     * @param managerId 经理编号
     * @return
     */
    boolean updateUserId(@Param("userId")Integer userId,@Param("managerId")Integer managerId);

    /**
     * 获取最大的Id
     * @return
     */
    List<Manager>getMaxId();

    /**
     * 通过经理Id查询经理
     * @param managerId 经理编号
     * @return
     */
    List<Manager>getManagerById(Integer managerId);
    Manager getUserIdByManId(Integer managerId);
    Manager getManagerByManagerId(Integer managerId);
}
