package com.jxd.service.impl;

import com.jxd.dao.ITeacherDao;
import com.jxd.model.Teacher;
import com.jxd.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    ITeacherDao teacherDao;
    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public List<Teacher> getAllTeacherByPage(Integer count, Integer page) {
        return teacherDao.getAllTeacherByPage(count,page);
    }

    @Override
    public List<Teacher> getTeachers(String teacherName) {
        return teacherDao.getTeachers(teacherName);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public boolean addATeacher(String teacherName,Integer userId) {
        return teacherDao.addATeacher(teacherName,userId);
    }

    @Override
    public boolean delTeacherById(Integer teacherId) {
        return teacherDao.delTeacherById(teacherId);
    }

    @Override
    public List<Teacher> getAllTeacherById(Integer teacherId) {
        return teacherDao.getAllTeacherById(teacherId);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public boolean updateUserId(Integer userId, Integer teacherId) {
        return teacherDao.updateUserId(userId, teacherId);
    }

    @Override
    public Teacher getUserIdByTeaId(Integer teacherId) {
        return teacherDao.getUserIdByTeaId(teacherId);
    }

    @Override
    public List<Teacher> getMaxId() {
        return teacherDao.getMaxId();
    }

    @Override
    public List<Teacher> getTeacherById(Integer teacherId) {
        return teacherDao.getTeacherById(teacherId);
    }
}
