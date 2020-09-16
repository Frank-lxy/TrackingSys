package com.jxd.service.impl;

import com.jxd.dao.IClazzDao;
import com.jxd.dao.ICourseDao;
import com.jxd.model.Course;
import com.jxd.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    ICourseDao courseDao;

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public List<Course> getCourseIdByName(String courseName) {
        return courseDao.getCourseIdByName(courseName);
    }


}
