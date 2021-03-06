package com.jxd.service;

import com.jxd.model.Teacher;

import java.util.List;

public interface ITeacherService {
    /**
     * 获取全部的教师
     * @return
     */
    List<Teacher> getAllTeacher();
    /**
     * 对教师的全部信息进行分页
     * @param count
     * @param page
     * @return
     */
    List<Teacher> getAllTeacherByPage( Integer count,  Integer page);
    /**
     * 通过教师名进行筛选查询
     * @param teacherName
     *
     * @return
     */
    List<Teacher>getTeachers( String teacherName);

    /**
     * 新增教师
     * @param teacher
     * @return
     */
    boolean addTeacher(Teacher teacher);

    /**
     * 在user新增教师信息时同时在教师表中新增一条数据
     * @param teacherName
     * @param userId
     * @return
     */
    boolean addATeacher(String teacherName,Integer userId);

    /**
     * 通过id删除教师
     * @param teacherId
     * @return
     */
    boolean delTeacherById(Integer teacherId);

    /**
     * 通过id查询教师
     * @param teacherId
     * @return
     */
    List<Teacher>getAllTeacherById(Integer teacherId);

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    boolean updateTeacher( Teacher teacher);

    /**
     * 把userId插入到teacherId对应数据中
     * @param userId
     * @param teacherId
     * @return
     */
    boolean updateUserId(Integer userId,Integer teacherId);
    Teacher getUserIdByTeaId(Integer teacherId);
    /**
     * 获取最大的Id
     * @return
     */
    List<Teacher>getMaxId();

    /**
     * 通过Id查询教师信息
     * @param teacherId
     * @return
     */
    List<Teacher>getTeacherById(Integer teacherId);
}
