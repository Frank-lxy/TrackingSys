package com.jxd.service;

import com.jxd.model.Department;
import com.jxd.model.Manager;

import java.util.List;

public interface IManagerService {
List<Manager>getAllManager();
    List<Manager> getAllManagerByPage(Integer count,Integer page);
    List<Manager> getManagers(String managerName,String departmentName);
    boolean addManager(Manager manager);
    boolean delManagerById(Integer managerId);
    boolean updateUserId(Integer userId,Integer managerId);
    Manager getManagerByManagerId(Integer managerId);
    boolean updateManager(Manager manager);
    boolean addAManager(String managerName,Integer userId);
    List<Manager>getMaxId();
    List<Manager>getManagerById(Integer managerId);
}
