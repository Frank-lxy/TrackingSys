package com.jxd.controller;

import com.jxd.model.*;
import com.jxd.service.ISassessService;
import com.jxd.service.IScoreService;
import com.jxd.service.IStudentService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
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
@SessionAttributes({"clazzes"})
public class ScoreController {
    @Autowired
    private IScoreService scoreService;
    @Autowired
    private ISassessService sassessService;
    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/addOrEditScore",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String addOrEditScore(Score score){
        Integer stuId = score.getStudentId();
        Score score1 = scoreService.getScore(score);
        if (score1 == null){
            boolean isAdd = scoreService.addScore(score);
            if (isAdd){
                return "已评分";
            }else {
                return "评价失败";
            }
        }else {
            boolean isEdit = scoreService.editScore(score);
            if (isEdit){
                return "已修改";
            }else {
                return "修改失败";
            }
        }

    }
    @RequestMapping("/scoreList")
    public String scoreList(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Clazz> clazzes = scoreService.getClazzListByTchName(user.getUserName());
        Clazz clazz = clazzes.get(0);
        model.addAttribute("clazzes",clazzes);
        model.addAttribute("clazz",clazz);
        return "scoreList";
    }

    @RequestMapping("/sassessDetailed")
    public String sassessDetailed(Integer studentId, Model model){
        Sassess sassess = sassessService.getSassessByStuId(studentId);
        model.addAttribute("sassess",sassess);
        model.addAttribute("studentId",studentId);
        return "sassessDetailed";
    }
    @RequestMapping(value = "/getAllCourseByClassId")
    @ResponseBody
    public List<Map<String,Object>> getAllCourseByClassId(Integer classId){
        //某个班期的所学课程
        List<Map<String,Object>> list = scoreService.getAllCourseByClassId(classId);
        return list;
    }
    @RequestMapping(value = "/getAllScoreInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON getAllScoreInfo(Integer classId, Integer limit, Integer page, String studentName){
        //存放封装的学生和成绩信息
        List<Map<String,Object>> list = new ArrayList<>();
        Integer count = limit*(page - 1);
        //某个班期的全部学生
        List<Student> students = scoreService.getAllStudent(classId,studentName);
        List<Student> students1 = scoreService.getStudentPaging(classId,count,limit,studentName);

        //把每一个学生的信息和对应成绩进行封装
        for (Student student:students1){
            //每个学生的各科成绩
            List<Map<String,Object>> scores = scoreService.getScoreByStuId(student.getStudentId(),student.getClassId());
           //把每个学生信息和他的各科成绩存储在map中
            Map<String,Object> map = new HashMap<>();
            map.put("studentId",student.getStudentId());
            map.put("studentName",student.getStudentName());
            map.put("sex",student.getSex());
            map.put("graduate",student.getGraduate());
            map.put("homeTown",student.getHomeTown());
            for (Map score:scores){
                if(score.get("score")==null){
                    map.put(  Integer.toString((Integer)score.get("courseId")),"待评价");
                }else {
                    map.put(  Integer.toString((Integer)score.get("courseId")),score.get("score"));
                }
            }
            //把map添加到list中
            list.add(map);
        }
        //转换为json数组
        JSONArray jsonArray = JSONArray.fromObject(list);
        //创建json对象，用于返回layui表格需要的数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",students.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);

        return jsonObject;
    }


    @RequestMapping(value = "/getDetailInfoById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON  getDetailInfoById(Integer studentId, HttpSession session){
        //存放封装的学生和成绩信息
        List<Map<String,Object>> list = new ArrayList<>();
        //评价人
        User user = (User) session.getAttribute("user");
        //学生评价
        Sassess sassess = sassessService.getSassessByStuId(studentId);

        Map<String,Object> stu = studentService.getStuInfoById(studentId);

            //每个学生的各科成绩
            List<Map<String,Object>> scores = scoreService.getScoreByStuId((Integer) stu.get("studentId"),(Integer) stu.get("classId"));
            //把每个学生信息和他的各科成绩存储在map中
            Map<String,Object> map = new HashMap<>();
            map.put("school","学习评价");
            map.put("clazz",stu.get("clazz"));
            map.put("teacher",user.getUserName());
            for (Map score:scores){
                if(score.get("score")==null){
                    map.put(  Integer.toString((Integer)score.get("courseId")),"待评价");
                }else {
                    map.put(  Integer.toString((Integer)score.get("courseId")),score.get("score"));
                }
            }
            if(sassess != null){
                map.put("evaluate",sassess.getEvaluate());
            }else {
                map.put("evaluate"," ");
            }
            //把map添加到list中
            list.add(map);
        //转换为json数组
        JSONArray jsonArray = JSONArray.fromObject(list);
        //创建json对象，用于返回layui表格需要的数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",1);//一共有多少条数据
        jsonObject.put("data",jsonArray);

        return jsonObject;
    }
}
