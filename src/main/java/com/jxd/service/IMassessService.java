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
     *根据状态获取经理的评价
     * @param studentName
     * @param managerId
     * @param tState
     * @return
     */
    List<Map<String,Object>> getMassessList(String studentName,Integer managerId,Integer tState);

    /**
     *根据状态获取经理的评价   分页
     * @param count
     * @param pageSize
     * @param studentName
     * @param managerId
     * @param tState
     * @return
     */
    List<Map<String,Object>>  getMassessLists(Integer count, Integer pageSize, String studentName,Integer managerId,Integer tState);

    /**
     * 根据经理id获取所有经理评分
     * @param managerId
     * @return
     */
    List<Map<String,Object>>  getAllMassessList(Integer managerId);

    /**
     * 获取所有学生
     * @param studentId
     * @return
     */
    Student getStudentById(Integer studentId);

    /**
     * 添加评价
     * @param massess
     * @return
     */
    boolean addMassess(Massess massess);

    /**
     * 根据经理评价表id获得评价
     * @param maId
     * @return
     */
    Massess getMassessById(Integer maId);

    /**
     * 修改评价
     * @param massess
     * @return
     */
    boolean editMassess(Massess massess);

    /**
     * 修改评价的状态
     * @param studentId
     * @param tState
     * @return
     */
    boolean editMassessState(Integer studentId,Integer tState);

    /**
     * 删除评价
     * @param massess
     * @return
     */
    boolean delMassessById(Massess massess);

    /**
     * 添加评价后添加一行空的评价作为下一阶段的评价
     * @param studentId
     * @param tState
     * @return
     */
    boolean addMassessNone(Integer studentId,Integer tState);

    /**
     * 根据学生id和状态获得所有评价
     * @param studentId
     * @param tState
     * @return
     */
    Massess getMassess(Integer studentId,Integer tState);

    /**
     * 获取学员列表
     * @return 学员列表
     */
    List<Student> getAllMStudent(String studentName,String jobId,Integer managerId);

    /**
     * 获取学员列表  分页
     * @param count
     * @param pageSize
     * @param studentName
     * @param jobId
     * @param managerId
     * @return
     */
    List<Map<String,Object>> getMStudentPaging(Integer count,Integer pageSize,String studentName,String jobId,Integer managerId);

}
