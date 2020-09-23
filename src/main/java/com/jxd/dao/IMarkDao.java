package com.jxd.dao;

import com.jxd.model.Manager;
import com.jxd.model.Mark;
import com.jxd.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-15 15:02
 **/
public interface IMarkDao {

    /**
     * 获得用户的部门id
     * @param userId 用户Id
     * @return 经理对象
     */
    Manager getDepatermentId(Integer userId);

    /**
     * 增加评分
     * @param mark 成绩对象
     * @return 是否添加成功
     */
    boolean addMark(Mark mark);

    /**
     * 修改评分
     * @param mark 成绩对象
     * @return 是否修改成功
     */
    boolean editMark(Mark mark);

    /**
     * 获得员工的评分项成绩
     * @param studentId 学生Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @param assessItemId 评价项Id
     * @return 成绩对象
     */
    Mark getMark(@Param("studentId") Integer studentId, @Param("tState") Integer tState,@Param("assessItemId")Integer assessItemId);

    /**
     * 通过部门id获得该部门的所有评价项
     * @param departmentId 部门Id
     * @return 评价项集合
     */
    List<Map<String,Object>> getAllAssessItemByDeptId(Integer departmentId);

    /**
     * 查询全部学生
     * @param managerId 经理Id
     * @param studentName 学生姓名
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 学生集合
     */
    List<Student> getAllStudent(@Param("managerId") Integer managerId ,@Param("studentName") String studentName,@Param("tState") Integer tState);


    /**
     * 分页查询 获取某个员工在某个入职状态时的各评分项的成绩
     * @param count 跳过的数量
     * @param pageSize 取得数据数量
     * @param studentName 员工姓名
     * @return 学生集合
     */
    List<Student> getStudentPaging(@Param("managerId") Integer managerId,@Param("count") Integer count, @Param("pageSize") Integer pageSize,@Param("studentName") String studentName,@Param("tState") Integer tState);

    /**
     * 获取某个员工在某个入职状态时的各评分项的成绩
     * @param studentId 学生id
     * @return 评分集合
     */
    List<Map<String,Object>> getMarkByStuId(@Param("studentId") Integer studentId,@Param("departmentId") Integer departmentId,@Param("tState") Integer tState);
}
