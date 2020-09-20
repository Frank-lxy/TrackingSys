package com.jxd.dao;

import com.jxd.model.Coursesel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseselDao {
    boolean addCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);
    List<Coursesel> getCourseIdById(Integer classId);
    boolean updateCoursesel(@Param("courseId") Integer courseId, @Param("classId")Integer classId);
}
