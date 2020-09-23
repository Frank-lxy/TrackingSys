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
 * @Author:Chai Hong Fang
 * @Description:
 * @Date:2020/9/12 9:33
 * @Version:1.0
 */
@Controller
@SessionAttributes({"clazzes","clazzIdOfStu"})
public class ScoreController {
    @Autowired
    private IScoreService scoreService;
    @Autowired
    private ISassessService sassessService;
    @Autowired
    private IStudentService studentService;

    /**
     * 增加或修改分数
     * @param score 分数对象
     * @return 是否成功
     */
    @RequestMapping(value = "/addOrEditScore",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String addOrEditScore(Score score){
        Score score1 = scoreService.getScore(score);
        //判断是否给某个学生的某个科目打过分
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

    /**
     * 获取成绩列表
     * @param session 会话
     * @param model 模型
     * @return 成绩列表
     */
    @RequestMapping("/scoreList")
    public String scoreList(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        //根据老师姓名查找班期
        List<Clazz> clazzes = scoreService.getClazzListByTchName(user.getUserName());

        //最新班期
        Clazz clazz = clazzes.get(0);
        model.addAttribute("clazzes",clazzes);
        model.addAttribute("clazz",clazz);
        return "scoreList";
    }

    /**
     * 查看评价详情
     * @param studentId 学生id
     * @param model 模型
     * @return sassessDetailed页面
     */
    @RequestMapping("/sassessDetailed")
    public String sassessDetailed(Integer studentId,Model model){
        Sassess sassess = sassessService.getSassessByStuId(studentId);
        model.addAttribute("sassess",sassess);
        model.addAttribute("studentId",studentId);

        //根据学生id查找学生信息
        Map<String,Object> stu = studentService.getStuInfoById(studentId);
        model.addAttribute("clazzIdOfStu",(Integer) stu.get("classId"));
        return "sassessDetailed";
    }

    /**
     * 根据课程id得到所有课程
     * @param classId 班级id
     * @return 班级列表集合
     */
    @RequestMapping(value = "/getAllCourseByClassId")
    @ResponseBody
    public List<Map<String,Object>> getAllCourseByClassId(Integer classId){
        //某个班期的所学课程
        List<Map<String,Object>> list = scoreService.getAllCourseByClassId(classId);
        return list;
    }

    /**
     * 分页获取成绩信息
     * @param classId 班级id
     * @param limit 跳过的数据数量
     * @param page 取得的数据的数量
     * @param studentName 学生姓名
     * @return json数据
     */
    @RequestMapping(value = "/getAllScoreInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON getAllScoreInfo(Integer classId, Integer limit, Integer page, String studentName){
        //存放封装的学生和成绩信息
        List<Map<String,Object>> list = new ArrayList<>();
        Integer count = limit*(page - 1);
        //通过班期查找学生
        List<Student> students = scoreService.getAllStudent(classId,studentName);
        //分页查找
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
                if(score.get("score") == null){
                    map.put(  Integer.toString((Integer)score.get("courseId")),"");
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

    /**
     * 查看分数详细信息
     * @param studentId 学生id
     * @param session 会话
     * @param model 模型
     * @return json数组
     */
    @RequestMapping(value = "/getDetailInfoById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON  getDetailInfoById(Integer studentId, HttpSession session,Model model){
        //存放封装的学生和成绩信息
        List<Map<String,Object>> list = new ArrayList<>();
        //评价人
        User user = (User) session.getAttribute("user");
        //学生评价
        Sassess sassess = sassessService.getSassessByStuId(studentId);
        //根据学生id查找学生信息
        Map<String,Object> stu = studentService.getStuInfoById(studentId);
        //学生的各科成绩
        List<Map<String,Object>> scores = scoreService.getScoreByStuId((Integer) stu.get("studentId"),(Integer) stu.get("classId"));

        Integer clazzIdOfStu = (Integer) stu.get("classId");
        model.addAttribute("clazzIdOfStu",clazzIdOfStu);
        //把每个学生信息和他的各科成绩存储在map中
        Map<String,Object> map = new HashMap<>();
        map.put("school","学习评价");
        map.put("clazz",stu.get("clazz"));
        map.put("teacher",user.getUserName());
        for (Map score:scores){
            if(score.get("score") == null){
                map.put(  Integer.toString((Integer)score.get("courseId")),"");
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
