package com.jxd.dao;

import com.jxd.model.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseDao {

    /**
     * 编辑课程
     * @param course 要编辑的课程
     * @return 是否编辑成功
     */
    boolean editCourse(Course course);

    /**
     * 根据课程id查询课程信息
     * @param courseId 课程id
     * @return 课程信息
     */
    Course getCourseById(Integer courseId);

    /**
     * 新增课程
     * @param course 要新增的课程
     * @return 是否增加成功
     */
    boolean addCourse(Course course);

    /**
     * 根据课程名称查询课程是否存在
     * @param courseName 课程名称
     * @return 是否存在
     */
    Course getCourseByName(String courseName);

    /**
     * 根据id删除课程
     * @param courseId 课程id
     * @return 是否删除成功
     */
    boolean deleteCourseById(Integer courseId);

    /**
     * 分页查询课程
     * @param count 跳过的数据
     * @param pageSize 每页显示条数
     * @param courseName 课程名称
     * @return 课程列表
     */
    List<Course> getCoursePaging(@Param("count") Integer count, @Param("pageSize") Integer pageSize, @Param("courseName") String courseName);

    /**
     * 获取课程列表
     * @param courseName 课程名称
     * @return 课程列表
     */
    List<Course> getAllCourse(@Param("courseName")String courseName);

    /**
     * 通过课程名获取课程Id
     * @param courseName 课程名称
     * @return 课程列表
     */
    List<Course> getCourseIdByName(String courseName);
}
