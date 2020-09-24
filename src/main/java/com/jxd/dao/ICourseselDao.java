package com.jxd.dao;

import com.jxd.model.Coursesel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseselDao {
    /**
     * 课程新增
     * @param courseId 课程编号
     * @param classId 班期编号
     * @return
     */
    boolean addCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);


    /**
     * 通过classId获取课程ID
     * @param classId 班期编号
     * @return
     */
    List<Coursesel> getCourseIdById(Integer classId);

    /**
     * 删除选课表
     * @param classId 班期编号
     * @return
     */
    boolean delCourseselById(Integer classId);
}
