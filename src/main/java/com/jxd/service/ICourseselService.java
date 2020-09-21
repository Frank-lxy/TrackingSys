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
Coursesel getCourseselIdById(Integer courseId, Integer classId);
    /**
     * 更新选课表
     * @param courseId
     * @param classId
     * @return
     */
    boolean updateCoursesel( Integer courseId, Integer classId,Integer courseselId);
    boolean delCourseselById(Integer classId);
}
