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

    List<Map<String,Object>> getMassessList(String studentName,Integer managerId,Integer tState);

    List<Map<String,Object>>  getMassessLists(Integer count, Integer pageSize, String studentName,Integer managerId,Integer tState);

    List<Map<String,Object>>  getAllMassessList(Integer managerId);

    Student getStudentById(Integer studentId);

    boolean addMassess(Massess massess);

    Massess getMassessById(Integer maId);

    boolean editMassess(Massess massess);

    boolean editMassessState(Integer studentId,Integer tState);

    boolean delMassessById(Massess massess);

    boolean addMassessNone(Integer studentId,Integer tState);

    Massess getMassess(Integer studentId,Integer tState);

}
