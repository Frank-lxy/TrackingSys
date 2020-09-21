package com.jxd.service;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;

import java.util.List;

public interface IClazzService {
    //获取全部的班期
    List<Clazz> getAllClazz();
    //获取最大的班期信息
    List<Clazz>getMaxClazz();
    //通过名字去查询全部的班期信息
    List<Clazz>getAllClazzByName(String clazz);
    //通过classId查询对应的clazz信息
    Clazz getClazzById(Integer classId);
    //通过clazz和teacherName查询后筛选
    List<Clazz> getClazz( String clazz, String teacherName);
    //获取全部信息后分页
    List<Clazz> getAllClazzByPage( Integer count,  Integer page);
    //新增班期
    boolean addClazz( String clazz, String teacherName);
    //更新班期中的数据通过classId
    boolean updateClazz( String clazz, String teacherName, Integer classId);
    //获取全部的学生信息
    List<Student> getAllStudent();
    //获取全部的课程
    List<Course> getAllCourse();
    //获取全部课程后分页
    List<Course> getAllCourseByPage( Integer count, Integer page);
    //对选课表进行新增
    boolean addCourse( Integer courseId,Integer classID);
    //通过classId更新学生信息
    boolean updateStudentClazz(Integer classId);
    //获取全部的教师信息
    List<User> getAllTeacher();
    Clazz getClazzByClassId(Integer classId);
}
