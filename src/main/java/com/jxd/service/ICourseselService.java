package com.jxd.service;

import com.jxd.model.Coursesel;

import java.util.List;

public interface ICourseselService {
    boolean addCoursesel(Integer courseId,Integer classId);
    List<Coursesel> getCourseIdById(Integer classId);
    boolean updateCoursesel(Integer courseId,Integer classId);
}
