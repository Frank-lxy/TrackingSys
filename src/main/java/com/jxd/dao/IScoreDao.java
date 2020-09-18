package com.jxd.dao;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Score;
import com.jxd.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IScoreDao {

    /**
     * 查询每期的课程
     * @param classId 课程id
     * @return 课程集合
     */
    List<Map<String,Object>> getAllCourseByClassId(Integer classId);

    /**
     * 根据学生id，查找他的全部成绩
     * @param studentId 学生id
     * @param classId 课程id
     * @return 学生全部成绩信息集合
     */
    List<Map<String,Object>> getScoreByStuId(@Param("studentId") Integer studentId,@Param("classId") Integer classId);

    /**
     * 查询全部学生
     * @param classId 班级id
     * @param studentName 学生姓名
     * @return 学生集合
     */
    List<Student> getAllStudent(@Param("classId") Integer classId,@Param("studentName") String studentName);

    /**
     * 分页查询学生
     * @param classId 班级id
     * @param count 跳过的数量
     * @param pageSize 取得数据的数量
     * @param studentName 学生姓名
     * @return
     */
    List<Student> getStudentPaging(@Param("classId") Integer classId,@Param("count") Integer count,@Param("pageSize") Integer pageSize, @Param("studentName") String studentName);

    /**
     * 根据教师id查找所带期次
     * @param teacherName
     * @return 期次集合
     */
    List<Clazz> getClazzListByTchName(String teacherName);

    /**
     * 查找某个同学的某个课程的成绩
     * @param score 成绩
     * @return 成绩对象
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
