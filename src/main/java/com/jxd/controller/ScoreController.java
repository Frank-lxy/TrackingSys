package com.jxd.controller;

import com.jxd.model.Course;
import com.jxd.model.Score;
import com.jxd.model.Student;
import com.jxd.service.IScoreService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Chai HongFang
 * @Description:
 * @Date:2020/9/12 9:33
 * @Version:1.0
 */
@Controller
public class ScoreController {
    @Autowired
    private IScoreService scoreService;
    @RequestMapping(value = "/getAllCourseByClassId")
    @ResponseBody
    public List<Map<String,Object>> getAllCourseByClassId(Integer classId){
        List<Map<String,Object>> list = scoreService.getAllCourseByClassId(classId);
        return list;
    }
    @RequestMapping("/getAllScoreInfo")
    @ResponseBody
    public JSON getAllScoreInfo(Integer classId, Integer limit, Integer page, String studentName){

        List<Map<String,Object>> list = new ArrayList<>();//存放封装的学生和成绩信息
        Integer count = limit*(page - 1);
        List<Student> students = scoreService.getAllStudent(classId);
        List<Student> students1 = scoreService.getStudentPaging(classId,count,limit,studentName);

        //把每一个学生的信息和对应成绩进行封装
        for (Student student:students1){
            List<Map<String,Object>> scores = scoreService.getScoreByStuId(student.getStudentId(),student.getClassId());
            Map<String,Object> map = new HashMap<>();

            map.put("studentId",student.getStudentId());
            map.put("studentName",student.getStudentName());
            map.put("sex",student.getSex());
            map.put("graduate",student.getGraduate());
            map.put("homeTown",student.getHomeTown());
            for (Map score:scores){
                map.put(  Integer.toString((Integer)score.get("courseId")),score.get("score"));
            }
            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        //创建json对象，用于返回layui表格需要的数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",students.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);

        return jsonObject;
    }





}
