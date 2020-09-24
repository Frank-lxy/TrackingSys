package com.jxd.service;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Score;
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
     * @param classId 班级id
     * @param studentName 学生姓名
     * @return 学生集合
     */
    List<Student> getAllStudent(Integer classId,String studentName);

    /**
     * 分页查询学生
     * @param classId 班级id
     * @param count 跳过的数量
     * @param pageSize 取得数据数量
     * @param studentName 学生姓名
     * @return 学生集合
     */
    List<Student> getStudentPaging(Integer classId,Integer count, Integer pageSize, String studentName);

    /**
     * 获取某位学生的全部成绩
     * @param studentId 学生id
     * @param classId 班期id
     * @return 成绩信息集合
     */
    List<Map<String,Object>> getScoreByStuId(Integer studentId,Integer classId);

    /**
     * 根据教师姓名查找所带班期
     * @param teacherName 教师姓名
     * @return 班期集合
     */
    List<Clazz> getClazzListByTchName(String teacherName);

    /**
     * 查找某个同学的某个课程的成绩
     * @param score 分数
     * @return 分数对象
     */
    Score getScore(Score score);

    /**
     * 增加成绩
     * @param score 成绩对象
     * @return 是否添加成功
     */
    boolean addScore(Score score);

    /**
     * 修改成绩
     * @param score 成绩对象
     * @return 是否修改成功
     */
    boolean editScore(Score score);

}
