package com.jxd.service.impl;

import com.jxd.dao.IStudentDao;
import com.jxd.model.*;
import com.jxd.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/13 9:38
 * @Version 1.0
 */
@Service

public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentDao studentDao;

    @Override
    public boolean editStudent(Student student) {
        return studentDao.editStudent(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public Clazz getClazzById(Integer classId) {
        return studentDao.getClazzById(classId);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public List<Clazz> getClazzList() {
        return studentDao.getClazzList();
    }

    @Override
    public List<User> getManagerList() {
        return studentDao.getManagerList();
    }

    @Override
    public boolean deleteStudentById(Integer studentId) {
        return studentDao.deleteStudentById(studentId);
    }

    @Override
    public boolean deleteStudentBatch(String studentIds) {
        return studentDao.deleteStudentBatch(studentIds);
    }

    @Override
    public Map<String, Object> getStudentDetailedById(Integer studentId) {
        return studentDao.getStudentDetailedById(studentId);
    }

    @Override
    public List<Map<String,Object>> getStudentPaging(Integer count, Integer pageSize, String studentName) {
        return studentDao.getStudentPaging(count, pageSize, studentName);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }
}
