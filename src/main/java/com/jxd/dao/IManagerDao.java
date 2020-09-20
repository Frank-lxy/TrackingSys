package com.jxd.dao;

import com.jxd.model.Department;
import com.jxd.model.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IManagerDao {
    List<Manager>getAllManager();
    List<Manager> getAllManagerByPage(@Param("count") Integer count, @Param("page") Integer page);
    List<Manager>getManagers(@Param("managerName") String managerName, @Param("departmentName") String departmentName);
    boolean addManager(Manager manager);
    boolean addAManager(@Param("managerName")String managerName,@Param("userId")Integer userId);
    boolean delManagerById(Integer managerId);
    List<Manager>getAllManagerById(Integer managerId);
    boolean updateManager( Manager manager);
    boolean updateUserId(@Param("userId")Integer userId,@Param("managerId")Integer managerId);
    List<Manager>getMaxId();
    List<Manager>getManagerById(Integer managerId);
}
