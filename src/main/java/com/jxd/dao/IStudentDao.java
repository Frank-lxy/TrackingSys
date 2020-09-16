package com.jxd.dao;

import com.jxd.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IStudentDao {

    /**
     * 编辑学员
     * @param student 要编辑的学员
     * @return 是否编辑成功
     */
    boolean editStudent(Student student);

    /**
     * 根据学员id获取学员状态
     * @param studentId  学员id
     * @return 学员状态
     */
    String getStateByStudentId(Integer studentId);

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
     * 获取项目经理列表
     * @return 项目经理
     */
    List<User> getManagerList();

    /**
     * 新增学员
     * @param student 要新增的学员
     * @return 是否增加成功
     */
    boolean addStudent(Student student);

    /**
     * 获取班期列表
     * @return 班期
     */
    List<Clazz> getClazzList();

    /**
     * 根据id删除学员
     * @param studentId 学员编号
     * @return 是否删除成功
     */
    boolean deleteStudentById(Integer studentId);
    //删除该学员的学校评价表内容
    boolean deleteSassessByStudentId(Integer studentId);
    //删除该学员的成绩表内容
    boolean deleteScoreByStudentId(Integer studentId);
    //删除该学员的工作评价表内容
    boolean deleteMassessByStudentId(Integer studentId);
    //删除该学员的评分表内容
    boolean deleteAssessItemByStudentId(Integer studentId);

    /**
     * 根据id批量删除学员
     * @param studentIds 学员id字符串
     * @return 是否批量删除成功
     */
    boolean deleteStudentBatch(@Param("studentIds") String studentIds);

    /**
     * 根据学员编号查询学员基本信息
     * @param studentId 学员编号
     * @return 学员基本信息
     */
    Map<String,Object> getStudentDetailedById(Integer studentId);

    /**
     * 分页查询学员信息
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param studentName 学员名称
     * @return 学员列表
     */
    List<Map<String,Object>> getStudentPaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("studentName") String studentName,@Param("departmentId") String departmentId,@Param("jobId") String jobId);

    /**
     * 获取学员列表
     * @return 学员列表
     */
    List<Student> getAllStudent(@Param("studentName") String studentName,@Param("departmentId") String departmentId,@Param("jobId") String jobId);
}
