package com.jxd.service;

import com.jxd.model.Clazz;
import com.jxd.model.Department;
import com.jxd.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentService {

    /**
     * 编辑学员
     * @param student 要编辑的学员
     * @return 是否编辑成功
     */
    boolean editStudent(Student student);

    /**
     * 根据id查询学员信息
     * @param studentId 学员id
     * @return 学员信息
     */
    Student getStudentById(Integer studentId);

    /**
     * 根据id获取班期
     * @param classId 班期id
     * @return 班期
     */
    Clazz getClazzById(Integer classId);

    /**
     * 新增学员
     * @param student 要新增的学员
     * @return 是否增加成功
     */
    boolean addStudent(Student student);

    /**
     * 根据id删除学员
     * @param studentId 学员编号
     * @return 是否删除成功
     */
    boolean deleteStudentById(Integer studentId);

    /**
     * 根据id批量删除学员
     * @param studentIds 学员id字符串
     * @return 是否批量删除成功
     */
    boolean deleteStudentBatch(String studentIds);

    /**
     * 分页查询学员信息
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param studentName 学员名称
     * @return 学员列表
     */
    List<Student> getStudentPaging(Integer count, Integer pageSize, String studentName);

    /**
     * 获取学员列表
     * @return 学员列表
     */
    List<Student> getAllStudent();
}
