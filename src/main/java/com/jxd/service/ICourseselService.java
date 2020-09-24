package com.jxd.service;

import com.jxd.model.Coursesel;

import java.util.List;

public interface ICourseselService {
    /**
     * 课程新增
     * @param courseId
     * @param classId
     * @return
     */
    boolean addCoursesel( Integer courseId, Integer classId);

    /**
     * 通过classId获取课程ID
     * @param classId
     * @return
     */
    List<Coursesel> getCourseIdById(Integer classId);

    boolean delCourseselById(Integer classId);
}
