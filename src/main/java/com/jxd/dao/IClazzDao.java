package com.jxd.dao;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IClazzDao {
    List<Clazz> getAllClazz();
    List<Clazz> getMaxClazz();
    List<Clazz> getAllClazzByName(String clazz);
    Clazz getClazzByClassId(Integer classId);
    List<Clazz> getClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName);
    List<Clazz> getAllClazzByPage(@Param("count") Integer count, @Param("page") Integer page);
    boolean addClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName);
    boolean updateClazz(@Param("clazz") String clazz,@Param("teacherName") String teacherName,@Param("classId") Integer classId);
    List<Student> getAllStudent();
    List<Course> getAllCourse();
    List<Course> getAllCourseByPage(@Param("count") Integer count,@Param("page") Integer page);
    boolean addCourse(@Param("courseId") Integer courseId,@Param("classId")Integer classID);
    boolean updateStudentClazz(Integer classId);
    List<User> getAllTeacher();
}
