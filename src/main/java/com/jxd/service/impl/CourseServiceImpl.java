package com.jxd.service.impl;

import com.jxd.dao.ICourseDao;
import com.jxd.model.Course;
import com.jxd.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/12 11:17
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    ICourseDao courseDao;

    @Override
    public boolean editCourse(Course course) {
        return courseDao.editCourse(course);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseDao.getCourseByName(courseName);
    }

    @Override
    public boolean deleteCourseById(Integer courseId) {
        return courseDao.deleteCourseById(courseId);
    }

    @Override
    public List<Course> getCoursePaging(Integer count, Integer pageSize, String courseName) {
        return courseDao.getCoursePaging(count,pageSize,courseName);
    }

    @Override
    public List<Course> getAllCourse(String courseName) {
        return courseDao.getAllCourse(courseName);
    }

    @Override
    public List<Course> getCourseIdByName(String courseName) {
        return courseDao.getCourseIdByName(courseName);
    }
}
