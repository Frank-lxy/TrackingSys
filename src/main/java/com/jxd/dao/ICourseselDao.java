package com.jxd.dao;

import com.jxd.model.Coursesel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseselDao {
    /**
     * 课程新增
     * @param courseId
     * @param classId
     * @return
     */
    boolean addCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);

    /**
     * 获取选课表的id
     * @param courseId
     * @param classId
     * @return
     */
    Coursesel getCourseselIdById(@Param("courseId") Integer courseId, @Param("classId")Integer classId);

    /**
     * 通过classId获取课程ID
     * @param classId
     * @return
     */
    List<Coursesel> getCourseIdById(Integer classId);

    /**
     * 更新选课表
     * @param courseId
     * @param classId
     * @return
     */
    boolean updateCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId,@Param("courseselId") Integer courseselId);
    boolean delCourseselById(Integer classId);
}
