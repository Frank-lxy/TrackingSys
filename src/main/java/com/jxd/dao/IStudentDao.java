package com.jxd.dao;

import com.jxd.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IStudentDao {

    /**
     * 查询项目经理评价阶段列表
     * @return 评价阶段列表
     */
    List<TState> getTStateList();

    /**
     * 根据学员id查询项目经理评分
     * @param studentId 学员id
     * @return 项目经理评分列表
     */
    List<Massess> getMassessByStuId(Integer studentId);

    /**
     * 根据班期id分页查询学员列表
     * @param count 跳过的条数
     * @param limit 每页显示条目数
     * @param classId 班级id
     * @return 学员列表
     */
    List<Student> getStudentListByClassIdPaging(@Param("classId") Integer classId,@Param("managerId") Integer managerId,@Param("studentName") String studentName,@Param("count") Integer count,@Param("limit") Integer limit);

    /**
     * 根据班期id查询学员列表
     * @param classId 班期id
     * @return 学员列表
     */
    List<Student> getStudentListByClassId(@Param("classId") Integer classId,@Param("managerId") Integer managerId,@Param("studentName")String studentName);

    /**
     * 根据班级id获取课程列表
     * @param classId 班级id
     * @return 课程列表
     */
    List<Course> getCourseByClassId(Integer classId);

    /**
     * 编辑学员
     * @param student 要编辑的学员
     * @return 是否编辑成功
     */
    boolean editStudent(Student student);

    /**
     * 分页查询学员信息
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param studentName 学员名称
     * @return 学员列表
     */
    List<Map<String,Object>> getAllStudentByTidPaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("studentName") String studentName,@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("teacherName") String teacherName);

    /**
     * 获取学员列表
     * @return 学员列表
     */
    List<Student> getAllStudentByTid(@Param("studentName") String studentName,@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("teacherName") String teacherName);

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

    /**
     * 根据学生编号获得学生信息
     * @param studentId 学生编号
     * @return map集合
     */
   Map<String, Object> getStuInfoById(Integer studentId);

    /**
     * 根据学生编号获得学生信息
     * @param studentId 学生编号
     * @return map集合
     */
    Map<String, Object> getStudentInfoById(Integer studentId);

}
