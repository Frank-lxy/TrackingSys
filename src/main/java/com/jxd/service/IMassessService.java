package com.jxd.service;

import com.jxd.model.Massess;
import com.jxd.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-12 11:14
 **/
public interface IMassessService {

    /**
     *根据学生当前入职状态、评价表中的评价状态和经理Id查询出相关学生信息
     * @param studentName 学生姓名
     * @param managerId 经理Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 经理评分集合
     */
    List<Map<String,Object>> getMassessList(String studentName,Integer managerId,Integer tState);

    /**
     *根据学生当前入职状态、评价表中的评价状态和经理Id查询出相关学生信息 分页
     * @param count 跳过几条数据
     * @param pageSize 每页几条数据
     * @param studentName 学生姓名
     * @param managerId 经理Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 经理评分集合
     */
    List<Map<String,Object>>  getMassessLists(Integer count, Integer pageSize, String studentName,Integer managerId,Integer tState);

    /**
     * 根据经理id获取所有经理的员工
     * @param managerId 经理Id
     * @return 学生集合
     */
    List<Map<String,Object>>  getAllMassessList(Integer managerId);

    /**
     * 获取所有学生
     * @param studentId 学生Id
     * @return 学生对象
     */
    Student getStudentById(Integer studentId);

    /**
     * 添加评价
     * @param massess 经理评价对象
     * @return 是否添加成功
     */
    boolean addMassess(Massess massess);

    /**
     * 根据经理评价表id获得评价
     * @param maId 评价表Id
     * @return 经理评价对象
     */
    Massess getMassessById(Integer maId);

    /**
     * 修改评价
     * @param massess 经理评价对象
     * @return 是否修改成功
     */
    boolean editMassess(Massess massess);

    /**
     * 修改评价的状态
     * @param studentId 学生Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 是否修改成功
     */
    boolean editMassessState(Integer studentId,Integer tState);

    /**
     * 删除评价
     * @param massess 经理评价对象
     * @return 是否删除成功
     */
    boolean delMassessById(Massess massess);

    /**
     * 添加评价后添加一行空的评价作为下一阶段的评价
     * @param studentId 学生Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 是否添加成功
     */
    boolean addMassessNone(Integer studentId,Integer tState);

    /**
     * 根据学生id和状态获得所有评价
     * @param studentId 学生Id
     * @param tState 入职情况的状态（转正、一年、两年、三年）
     * @return 经理的评价对象
     */
    Massess getMassess(Integer studentId,Integer tState);

    /**
     * 获取学员列表
     * @param studentName 学生姓名
     * @param jobId 工作Id
     * @param managerId 经理Id
     * @return 学生集合
     */
    List<Student> getAllMStudent(String studentName,String jobId,Integer managerId);

    /**
     * 获取学员列表 分页
     * @param count 跳过几页数据
     * @param pageSize 每页几条数据
     * @param studentName 学生姓名
     * @param jobId 工作Id
     * @param managerId 经理Id
     * @return 学生集合
     */
    List<Map<String,Object>> getMStudentPaging(Integer count,Integer pageSize,String studentName,String jobId,Integer managerId);

}
