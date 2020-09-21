package com.jxd.service;

import com.jxd.model.Department;
import com.jxd.model.Manager;

import java.util.List;

public interface IManagerService {
    /**
     * 获取全部的经理
     * @return
     */
    List<Manager>getAllManager();

    /**
     * 对经理的全部信息进行分页
     * @param count
     * @param page
     * @return
     */
    List<Manager> getAllManagerByPage( Integer count, Integer page);
    Manager getUserIdByManId(Integer managerId);

    /**
     * 通过经理名和部门名进行筛选查询
     * @param managerName
     * @param departmentName
     * @return
     */
    List<Manager>getManagers( String managerName, String departmentName);

    /**
     * 新增经理
     * @param manager
     * @return
     */
    boolean addManager(Manager manager);

    /**
     *在user表新增数据时新增一条详细信息为空的数据，
     * @param managerName
     * @param userId
     * @return
     */
    boolean addAManager(String managerName,Integer userId);

    /**
     * 通过id删除经理信息
     * @param managerId
     * @return
     */
    boolean delManagerById(Integer managerId);

    /**
     * 通过id获取全部的经理信息
     * @param managerId
     * @return
     */
    List<Manager>getAllManagerById(Integer managerId);

    /**
     * 更新经理信息
     * @param manager
     * @return
     */
    boolean updateManager( Manager manager);

    /**
     * 把userId插入到新增的数据中
     * @param userId
     * @param managerId
     * @return
     */
    boolean updateUserId(Integer userId,Integer managerId);

    /**
     * 获取最大的Id
     * @return
     */
    List<Manager>getMaxId();

    /**
     * 通过经理Id查询经理
     * @param managerId
     * @return
     */
    List<Manager>getManagerById(Integer managerId);
    Manager getManagerByManagerId(Integer managerId);
}
