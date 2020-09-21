package com.jxd.service;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;

import java.util.List;

public interface IClazzService {
    List<Clazz> getAllClazz();
    Clazz getClazzByClassId(Integer classId);
    List<Clazz> getClazz(String clazz,String teacherName);
    List<Clazz> getAllClazzByPage(Integer count,Integer page);
    boolean updateClazz(String clazz,String teacherName,Integer classId);
    boolean addClazz(String clazz,String teacherName);
    List<Student> getAllStudent();
    List<Course> getAllCourse();
    List<Clazz> getMaxClazz();
    List<Clazz> getAllClazzByName(String clazz);
    List<Course> getAllCourseByPage(Integer count,Integer page);
    boolean addCourse(Integer courseId,Integer classID);
    boolean updateStudentClazz(Integer classId);
    List<User> getAllTeacher();
}
