package com.jxd.service.impl;

import com.jxd.dao.IClazzDao;
import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;
import com.jxd.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClazzServiceImpl implements IClazzService {
    @Autowired
    IClazzDao clazzDao;
    @Override
    public List<Clazz> getAllClazz() {
        return clazzDao.getAllClazz();
    }

    @Override
    public Clazz getClazzByClassId(Integer classId) {
        return clazzDao.getClazzByClassId(classId);
    }

    @Override
    public List<Clazz> getClazz(String clazz, String teacherName) {
        return clazzDao.getClazz(clazz,teacherName);
    }

    @Override
    public List<Clazz> getAllClazzByPage(Integer count, Integer page) {
        return clazzDao.getAllClazzByPage(count,page);
    }



    @Override
    public boolean updateClazz(String clazz, String teacherName, Integer classId) {
        return clazzDao.updateClazz(clazz,teacherName,classId);
    }

    @Override
    public boolean addClazz(String clazz, String teacherName) {
        return clazzDao.addClazz(clazz,teacherName);
    }

    @Override
    public List<Student> getAllStudent() {
        return clazzDao.getAllStudent();
    }

    @Override
    public List<Course> getAllCourse() {
        return clazzDao.getAllCourse();
    }

    @Override
    public List<Clazz> getMaxClazz() {
        return clazzDao.getMaxClazz();
    }

    @Override
    public List<Clazz> getAllClazzByName(String clazz) {
        return clazzDao.getAllClazzByName(clazz);
    }

    @Override
    public List<Course> getAllCourseByPage(Integer count, Integer page) {
        return clazzDao.getAllCourseByPage(count,page);
    }

    @Override
    public boolean addCourse(Integer courseId, Integer classID) {
        return clazzDao.addCourse(courseId, classID);
    }

    @Override
    public boolean updateStudentClazz(Integer classId) {
        return clazzDao.updateStudentClazz(classId);
    }

    @Override
    public List<User> getAllTeacher() {
        return clazzDao.getAllTeacher();
    }
}
