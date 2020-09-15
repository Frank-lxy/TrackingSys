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

    List<Map<String,Object>> getMassessList(@Param("studentName") String studentName,@Param("managerId") Integer managerId,@Param("tState") Integer tState);

    List<Map<String,Object>> getMassessLists(@Param("count") Integer count, @Param("pageSize") Integer pageSize,@Param("studentName") String studentName,@Param("managerId") Integer managerId,@Param("tState") Integer tState);

    Student getStudentById(Integer studentId);

    boolean addMassess(Massess massess);

    Massess getMassessById(Integer maId);

    boolean editMassess(Massess massess);

    boolean editMassessState(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

    boolean delMassessById(Massess massess);

    boolean addMassessNone(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

    Massess getMassess(@Param("studentId") Integer studentId,@Param("tState") Integer tState);

}
