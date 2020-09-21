package com.jxd.dao;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IClazzDao {
//获取全部的班期
    List<Clazz> getAllClazz();
    //获取最大的班期信息
    List<Clazz>getMaxClazz();
    //通过名字去查询全部的班期信息
    List<Clazz>getAllClazzByName(String clazz);
    //通过classId查询对应的clazz信息
    Clazz getClazzById(Integer classId);
    //通过clazz和teacherName查询后筛选
    List<Clazz> getClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName);
    //获取全部信息后分页
    List<Clazz> getAllClazzByPage(@Param("count") Integer count, @Param("page") Integer page);
    //新增班期
    boolean addClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName);
    //更新班期中的数据通过classId
    boolean updateClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName,@Param("classId") Integer classId);
    //获取全部的学生信息
    List<Student> getAllStudent();
    //获取全部的课程
    List<Course> getAllCourse();
    //获取全部课程后分页
    List<Course> getAllCourseByPage(@Param("count") Integer count,@Param("page") Integer page);
    //对选课表进行新增
    boolean addCourse(@Param("courseId") Integer courseId,@Param("classId")Integer classID);
    //通过classId更新学生信息
    boolean updateStudentClazz(Integer classId);
    //获取全部的教师信息
    List<User> getAllTeacher();
    Clazz getClazzByClassId(Integer classId);
}
