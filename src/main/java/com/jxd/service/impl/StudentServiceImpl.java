package com.jxd.service.impl;

import com.jxd.dao.IStudentDao;
import com.jxd.model.*;
import com.jxd.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/13 9:38
 * @Version 1.0
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    IStudentDao studentDao;

    @Override
    public List<TState> getTStateList() {
        return studentDao.getTStateList();
    }

    @Override
    public List<Massess> getMassessByStuId(Integer studentId) {
        return studentDao.getMassessByStuId(studentId);
    }

    @Override
    public List<Student> getStudentListByClassIdPaging(Integer classId,Integer managerId,String studentName,Integer count, Integer limit) {
        return studentDao.getStudentListByClassIdPaging(classId, managerId,studentName,count, limit);
    }

    @Override
    public List<Student> getStudentListByClassId(Integer classId,Integer managerId,String studentName) {
        return studentDao.getStudentListByClassId(classId,managerId,studentName);
    }

    @Override
    public List<Course> getCourseByClassId(Integer classId) {
        return studentDao.getCourseByClassId(classId);
    }

    @Override
    public boolean editStudent(Student student) {
        return studentDao.editStudent(student);
    }

    @Override
    public String getStateByStudentId(Integer studentId) {
        return studentDao.getStateByStudentId(studentId);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public Clazz getClazzById(Integer classId) {
        return studentDao.getClazzById(classId);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public List<Clazz> getClazzList() {
        return studentDao.getClazzList();
    }

    @Override
    public List<User> getManagerList() {
        return studentDao.getManagerList();
    }

    @Override
    public boolean deleteStudentById(Integer studentId) {
        return studentDao.deleteStudentById(studentId);
    }

    @Override
    public boolean deleteStudentBatch(String studentIds) {
        return studentDao.deleteStudentBatch(studentIds);
    }

    @Override
    public Map<String, Object> getStudentDetailedById(Integer studentId) {
        return studentDao.getStudentDetailedById(studentId);
    }

    @Override
    public List<Map<String,Object>> getStudentPaging(Integer count, Integer pageSize, String studentName,String departmentId,String jobId) {
        return studentDao.getStudentPaging(count, pageSize, studentName,departmentId,jobId);
    }

    @Override
    public List<Student> getAllStudent(String studentName,String departmentId,String jobId) {
        return studentDao.getAllStudent(studentName, departmentId, jobId);
    }

    @Override
    public List<Map<String, Object>> getAllStudentByTidPaging(Integer count, Integer pageSize, String studentName, String departmentId, String jobId, String teacherName) {
        return studentDao.getAllStudentByTidPaging(count,pageSize,studentName,departmentId,jobId,teacherName);
    }

    @Override
    public List<Student> getAllStudentByTid(String studentName, String departmentId, String jobId, String teacherName) {
        return studentDao.getAllStudentByTid(studentName,departmentId,jobId,teacherName);
    }

    @Override
    public Map<String, Object> getStuInfoById(Integer studentId) {
        return studentDao.getStuInfoById(studentId);
    }

    @Override
    public Map<String, Object> getStudentInfoById(Integer studentId) {
        return studentDao.getStudentInfoById(studentId);
    }
}
