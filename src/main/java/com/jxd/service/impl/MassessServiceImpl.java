package com.jxd.service.impl;

import com.jxd.dao.IMassessDao;
import com.jxd.model.Massess;
import com.jxd.model.Student;
import com.jxd.service.IMassessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-12 11:14
 **/
@Service
public class MassessServiceImpl implements IMassessService {

    @Autowired
    IMassessDao massessDao;

    @Override
    public List<Map<String,Object>> getMassessList( String studentName,Integer managerId,Integer tState) {
        return massessDao.getMassessList(studentName,managerId,tState);
    }

    @Override
    public List<Map<String, Object>> getMassessLists(Integer count, Integer pageSize, String studentName,Integer managerId,Integer tState) {
        return massessDao.getMassessLists(count,pageSize,studentName,managerId,tState);
    }

    @Override
    public List<Map<String, Object>> getAllMassessList(Integer managerId) {
        return massessDao.getAllMassessList(managerId);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return massessDao.getStudentById(studentId);
    }

    @Override
    public boolean addMassess(Massess massess) {
        return massessDao.addMassess(massess);
    }

    @Override
    public Massess getMassessById(Integer maId) {
        return massessDao.getMassessById(maId);
    }

    @Override
    public boolean editMassess(Massess massess) {
        return massessDao.editMassess(massess);
    }

    @Override
    public boolean editMassessState(Integer studentId, Integer tState) {
        return massessDao.editMassessState(studentId,tState);
    }

    @Override
    public boolean delMassessById(Massess massess) {
        return massessDao.delMassessById(massess);
    }

    @Override
    public boolean addMassessNone(Integer studentId, Integer tState) {
        return massessDao.addMassessNone(studentId,tState);
    }

    @Override
    public Massess getMassess(Integer studentId, Integer tState) {
        return massessDao.getMassess(studentId,tState);
    }

    @Override
    public List<Student> getAllMStudent(String studentName,String jobId, Integer managerId) {
        return massessDao.getAllMStudent(studentName,jobId,managerId);
    }

    @Override
    public List<Map<String, Object>> getMStudentPaging(Integer count, Integer pageSize, String studentName, String jobId, Integer managerId) {
        return massessDao.getMStudentPaging(count,pageSize,studentName,jobId,managerId);
    }

}
