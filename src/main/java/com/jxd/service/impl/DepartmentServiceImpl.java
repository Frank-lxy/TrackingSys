package com.jxd.service.impl;

import com.jxd.dao.IDepartmentDao;
import com.jxd.model.Department;
import com.jxd.model.Student;
import com.jxd.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/12 17:12
 * @Version 1.0
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    IDepartmentDao departmentDao;

    @Override
    public boolean editDepartment(Department department) {
        return departmentDao.editDepartment(department);
    }

    @Override
    public List<Department> getAllDep() {
        return departmentDao.getAllDep();
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public boolean addDepartment(Department department) {
        return departmentDao.addDepartment(department);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentDao.getDepartmentByName(departmentName);
    }

    @Override
    public boolean deleteDepartmentById(Integer departmentId) {
        return departmentDao.deleteDepartmentById(departmentId);
    }

    @Override
    public List<Student> getStudentByDepartmentId(Integer departmentId) {
        return departmentDao.getStudentByDepartmentId(departmentId);
    }

    @Override
    public List<Department> getDepartmentPaging(Integer count, Integer pageSize, String departmentName) {
        return departmentDao.getDepartmentPaging(count,pageSize,departmentName);
    }

    @Override
    public List<Department> getAllDepartment(String departmentName) {
        return departmentDao.getAllDepartment(departmentName);
    }
}
