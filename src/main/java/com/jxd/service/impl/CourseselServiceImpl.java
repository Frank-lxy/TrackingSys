package com.jxd.service.impl;

import com.jxd.dao.ICourseselDao;
import com.jxd.model.Course;
import com.jxd.service.ICourseService;
import com.jxd.service.ICourseselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseselServiceImpl implements ICourseselService {
    @Autowired
    ICourseselDao courseselDao;


    @Override
    public boolean addCoursesel(Integer courseId, Integer classId) {
        return courseselDao.addCoursesel(courseId,classId);
    }
}
