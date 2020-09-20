package com.jxd.service;

import com.jxd.model.Manager;
import com.jxd.model.Mark;
import com.jxd.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-15 15:01
 **/
public interface IMarkService {

    /**
     * 获得用户的部门id
     * @param userId
     * @return
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
     * @param mark
     * @return
     */
    boolean editMark(Mark mark);

    /**
     * 获得员工的评分项成绩
     * @param studentId
     * @param tState
     * @param assessItemId
     * @return
     */
    Mark getMark(Integer studentId, Integer tState,Integer assessItemId);

    /**
     * 通过部门id获得该部门的所有评价项
     * @param departmentId
     * @return
     */
    List<Map<String,Object>> getAllAssessItemByDeptId(Integer departmentId);

    /**
     * 查询全部员工
     * @return
     */
    List<Student> getAllStudent(Integer managerId ,String studentName,Integer tState);

    /**
     * 分页查询学生
     * @param count 跳过的数量
     * @param pageSize 取得数据数量
     * @param studentName 员工姓名
     * @return
     */
    List<Student> getStudentPaging(Integer managerId,Integer count, Integer pageSize, String studentName,Integer tState);

    /**
     * 获取某位学生的全部评分
     * @param studentId 学生id
     * @return 评分集合
     */
    List<Map<String,Object>> getMarkByStuId(Integer studentId,Integer departmentId,Integer tState);
}