package com.jxd.dao;

import com.jxd.model.Massess;
import com.jxd.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-12 11:14
 **/
public interface IMassessDao {

    /**
     *根据状态获取经理的评价
     * @param studentName
     * @param managerId
     * @param tState
     * @return
     */
    List<Map<String,Object>> getMassessList(@Param("studentName") String studentName,@Param("managerId") Integer managerId,@Param("tState") Integer tState);

    /**
     *根据状态获取经理的评价   分页
     * @param count
     * @param pageSize
     * @param studentName
     * @param managerId
     * @param tState
     * @return
     */
    List<Map<String,Object>> getMassessLists(@Param("count") Integer count, @Param("pageSize") Integer pageSize,@Param("studentName") String studentName,@Param("managerId") Integer managerId,@Param("tState") Integer tState);

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
    boolean editMassessState(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

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
    boolean addMassessNone(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

    /**
     * 根据学生id和状态获得所有评价
     * @param studentId
     * @param tState
     * @return
     */
    Massess getMassess(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

    /**
     * 获取学员列表
     * @return 学员列表
     */
    List<Student> getAllMStudent(@Param("studentName") String studentName,@Param("jobId") String jobId,@Param("managerId") Integer managerId);

    /**
     * 获取学员列表 分页
     * @param count
     * @param pageSize
     * @param studentName
     * @param jobId
     * @param managerId
     * @return
     */
    List<Map<String,Object>> getMStudentPaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("studentName") String studentName,@Param("jobId") String jobId,@Param("managerId") Integer managerId);

}
