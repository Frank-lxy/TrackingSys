package com.jxd.service.impl;

import com.jxd.dao.IMarkDao;
import com.jxd.model.Manager;
import com.jxd.model.Mark;
import com.jxd.model.Student;
import com.jxd.service.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-15 15:01
 **/
@Service
public class MarkServiceImpl implements IMarkService {
    @Autowired
    IMarkDao markDao;

    @Override
    public Manager getDepatermentId(Integer userId) {
        return markDao.getDepatermentId(userId);
    }

    @Override
    public boolean addMark(Mark mark) {
        return markDao.addMark(mark);
    }

    @Override
    public boolean editMark(Mark mark) {
        return markDao.editMark(mark);
    }

    @Override
    public Mark getMark(Integer studentId, Integer tState,Integer assessItemId) {
        return markDao.getMark(studentId,tState,assessItemId);
    }

    @Override
    public List<Map<String, Object>> getAllAssessItemByDeptId(Integer departmentId) {
        return markDao.getAllAssessItemByDeptId(departmentId);
    }

    @Override
    public List<Student> getAllStudent(Integer managerId, String studentName,Integer tState) {
        return markDao.getAllStudent(managerId,studentName,tState);
    }

    @Override
    public List<Student> getStudentPaging(Integer managerId, Integer count, Integer pageSize, String studentName,Integer tState) {
        return markDao.getStudentPaging(managerId,count,pageSize,studentName,tState);
    }

    @Override
    public List<Map<String, Object>> getMarkByStuId(Integer studentId, Integer departmentId,Integer tState) {
        return markDao.getMarkByStuId(studentId, departmentId,tState);
    }


}
