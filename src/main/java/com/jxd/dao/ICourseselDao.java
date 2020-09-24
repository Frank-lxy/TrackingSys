package com.jxd.dao;

import com.jxd.model.Coursesel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseselDao {
    /**
     * 课程新增
     * @param courseId 课程id
     * @param classId 班期id
     * @return 是否新增成功
     */
    boolean addCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);

    /**
     * 获取选课表的id
     * @param courseId 班期id
     * @param classId 班期id
     * @return 获取选课信息
     */
    Coursesel getCourseselIdById(@Param("courseId") Integer courseId, @Param("classId")Integer classId);

    /**
     * 通过classId获取课程ID
     * @param classId 班期id
     * @return 选课列表
     */
    List<Coursesel> getCourseIdById(Integer classId);

    /**
     * 更新选课表
     * @param courseId 课程id
     * @param classId 班期id
     * @param courseselId 选课id
     * @return 是否修改成功
     */
    boolean updateCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId,@Param("courseselId") Integer courseselId);

    /**
     * 根据班期id删除选课
     * @param classId 班期id
     * @return 是否删除成功
     */
    boolean delCourseselById(Integer classId);
}
