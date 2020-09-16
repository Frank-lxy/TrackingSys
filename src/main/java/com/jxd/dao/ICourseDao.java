package com.jxd.dao;

import com.jxd.model.Course;

import java.util.List;

public interface ICourseDao {
    List<Course> getAllCourse();
    List<Course>getCourseIdByName(String courseName);
}
