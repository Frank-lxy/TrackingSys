package com.jxd.dao;

import com.jxd.model.Department;
import com.jxd.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDepartmentDao {

    /**
     * 编辑部门
     * @param department 要编辑的部门
     * @return 是否编辑成功
     */
    boolean editDepartment(Department department);

    /**
     * 获取全部的部门
     * @return
     */
    List<Department> getAllDep();
    /**
     * 根据课程id查询部门信息
     * @param departmentId 部门id
     * @return 部门信息
     */
    Department getDepartmentById(Integer departmentId);

    /**
     * 新增部门
     * @param department 要新增的部门
     * @return 是否增加成功
     */
    boolean addDepartment(Department department);

    /**
     * 根据部门名称查询部门是否存在
     * @param departmentName 部门名称
     * @return 是否存在
     */
    Department getDepartmentByName(String departmentName);

    /**
     * 根据id删除部门
     * @param departmentId 部门id
     * @return 是否删除成功
     */
    boolean deleteDepartmentById(Integer departmentId);

    /**
     * 根据部门id查询学员
     * @param departmentId 部门编号
     * @return 学员列表
     */
    List<Student> getStudentByDepartmentId(Integer departmentId);

    /**
     * 分页查询部门
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param departmentName 部门名称
     * @return 部门列表
     */
    List<Department> getDepartmentPaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("departmentName") String departmentName);

    /**
     * 获取部门列表
     * @return 部门列表
     */
    List<Department> getAllDepartment(@Param("departmentName")String departmentName);
}
