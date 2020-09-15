package com.jxd.service;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;

import java.util.List;
import java.util.Map;

public interface IScoreService {
    /**
     * 查询每期的课程
     * @param classId 课程id
     * @return 课程集合
     */
    List<Map<String,Object>> getAllCourseByClassId(Integer classId);

    /**
     * 查询全部学生
     * @return
     */
    List<Student> getAllStudent(Integer classId);

    /**
     * 分页查询学生
     * @param count 跳过的数量
     * @param pageSize 取得数据数量
     * @param studentName 学生姓名
     * @return
     */
    List<Student> getStudentPaging(Integer classId,Integer count, Integer pageSize, String studentName);

    /**
     * 获取某位学生的全部成绩
     * @param studentId 学生id
     * @return 成绩集合
     */
    List<Map<String,Object>> getScoreByStuId(Integer studentId,Integer classId);

    /**
     * 根据教师id查找所带班期
     * @param teacherName 教师id
     * @return 班期集合
     */
    List<Clazz> getClazzListByTchName(String teacherName);
}
