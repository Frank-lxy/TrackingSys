package com.jxd.dao;

import com.jxd.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITeacherDao {
    List<Teacher> getAllTeacher();
    List<Teacher> getAllTeacherByPage(@Param("count") Integer count, @Param("page") Integer page);
    List<Teacher>getTeachers(@Param("teacherName") String teacherName);
    boolean addTeacher(Teacher teacher);
    boolean addATeacher(@Param("teacherName")String teacherName,@Param("userId")Integer userId);
    boolean delTeacherById(Integer teacherId);
    List<Teacher>getAllTeacherById(Integer teacherId);
    boolean updateTeacher( Teacher teacher);
    boolean updateUserId(@Param("userId")Integer userId,@Param("teacherId")Integer teacherId);
    List<Teacher>getMaxId();
    List<Teacher>getTeacherById(Integer teacherId);
}
