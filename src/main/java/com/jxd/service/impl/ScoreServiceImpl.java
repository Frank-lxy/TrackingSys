package com.jxd.service.impl;

import com.jxd.dao.IScoreDao;
import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:Chai HongFang
 * @Description:
 * @Date:2020/9/12 9:28
 * @Version:1.0
 */
@Service
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    IScoreDao scoreDao;
    @Override
    public  List<Map<String,Object>> getAllCourseByClassId(Integer classId) {
        return scoreDao.getAllCourseByClassId(classId);
    }

    @Override
    public List<Student> getAllStudent(Integer classId) {
        return scoreDao.getAllStudent(classId);
    }

    @Override
    public List<Student> getStudentPaging(Integer classId,Integer count, Integer pageSize, String studentName) {
        return scoreDao.getStudentPaging(classId,count,pageSize,studentName);
    }

    @Override
    public List<Map<String, Object>> getScoreByStuId(Integer studentId,Integer classId) {
        return scoreDao.getScoreByStuId(studentId,classId);
    }

    @Override
    public List<Clazz> getClazzListByTchName(String teacherName) {
        return scoreDao.getClazzListByTchName(teacherName);
    }
}
