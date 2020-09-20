package com.jxd.service;

import com.jxd.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITeacherService {
    List<Teacher> getAllTeacher();
    List<Teacher> getAllTeacherByPage( Integer count,  Integer page);
    List<Teacher>getTeachers( String teacherName);
    boolean addTeacher(Teacher teacher);
    boolean addATeacher(String teacherName,Integer userId);
    boolean delTeacherById(Integer teacherId);
    List<Teacher>getAllTeacherById(Integer teacherId);
    boolean updateTeacher( Teacher teacher);
    boolean updateUserId(Integer userId,Integer teacherId);
    List<Teacher>getMaxId();
    List<Teacher>getTeacherById(Integer teacherId);
}
