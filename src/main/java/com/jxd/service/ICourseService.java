package com.jxd.service;

import com.jxd.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourse();
    List<Course>getCourseIdByName(String courseName);
}
