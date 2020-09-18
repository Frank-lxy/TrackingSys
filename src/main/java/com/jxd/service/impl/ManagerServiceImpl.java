package com.jxd.service.impl;

import com.jxd.dao.IManagerDao;
import com.jxd.model.Manager;
import com.jxd.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    IManagerDao managerDao;

    @Override
    public List<Manager> getAllManager() {
        return managerDao.getAllManager();
    }

    @Override
    public List<Manager> getAllManagerByPage(Integer count, Integer page) {
        return managerDao.getAllManagerByPage(count,page);
    }

    @Override
    public List<Manager> getManagers(String managerName, String departmentName) {
        return managerDao.getManagers(managerName,departmentName);
    }

    @Override
    public boolean addManager(Manager manager) {
        return managerDao.addManager(manager);
    }

    @Override
    public boolean delManagerById(Integer managerId) {
        return managerDao.delManagerById(managerId);
    }

    @Override
    public boolean updateUserId(Integer userId, Integer managerId) {
        return managerDao.updateUserId(userId,managerId);
    }

    @Override
    public List<Manager> getAllManagerById(Integer managerId) {
        return managerDao.getAllManagerById(managerId);
    }

    @Override
    public boolean updateManager(Manager manager) {
        return managerDao.updateManager(manager);
    }


    @Override
    public List<Manager> getMaxId() {
        return managerDao.getMaxId();
    }

    @Override
    public List<Manager> getManagerById(Integer managerId) {
        return managerDao.getManagerById(managerId);
    }
}
