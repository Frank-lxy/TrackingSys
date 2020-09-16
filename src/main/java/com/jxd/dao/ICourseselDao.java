package com.jxd.dao;

import org.apache.ibatis.annotations.Param;

public interface ICourseselDao {
    boolean addCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);
}
