package com.jxd.controller;

import com.jxd.model.*;
import com.jxd.service.IMarkService;
import com.jxd.service.IMassessService;
import com.jxd.service.IStudentService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-15 14:59
 **/
@Controller
public class MarkController {

    @Autowired
    IMarkService markService;
    @Autowired
    IMassessService massessService;
    @Autowired
    IStudentService studentService;

    @RequestMapping("/markList")
    public String markList(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        model.addAttribute("departmentId",manager.getDepartmentId());
        model.addAttribute("tState",1);
        return "markList";
    }
    @RequestMapping("/markList1")
    public String markList1(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        model.addAttribute("departmentId",manager.getDepartmentId());
        model.addAttribute("tState",2);
        return "markList";
    }
    @RequestMapping("/markList2")
    public String markList2(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        model.addAttribute("departmentId",manager.getDepartmentId());
        model.addAttribute("tState",3);
        return "markList";
    }
    @RequestMapping("/markList3")
    public String markList3(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        model.addAttribute("departmentId",manager.getDepartmentId());
        model.addAttribute("tState",4);
        return "markList";
    }

    @RequestMapping("/massessDetailed")
    public String massessDetailed(Integer studentId, Integer tState,Model model){
        Massess massess = massessService.getMassess(studentId,tState);
        model.addAttribute("massess",massess);
        model.addAttribute("tState",tState);
        model.addAttribute("studentId",studentId);
        return "massessDetailed";
    }

    @RequestMapping(value = "/addOrEditMark",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String addOrEditMark(Mark mark){
        Integer studentId = mark.getStudentId();
        Integer tState = mark.gettState();
        Integer assessItemId = mark.getAssessItemId();
        Mark mark1 = markService.getMark(studentId,tState,assessItemId);
        if (mark1== null){
            boolean isAdd = markService.addMark(mark);
            if (isAdd){
                return "已评分";
            }else {
                return "评价失败";
            }
        }else {
            boolean isEdit = markService.editMark(mark);
            if (isEdit){
                return "已修改";
            }else {
                return "修改失败";
            }
        }

    }

    @RequestMapping(value = "/getAllAssessItemByDeptId")
    @ResponseBody
    public List<Map<String,Object>> getAllAssessItemByDeptId(Integer departmentId){
        //某个部门的评价项
        List<Map<String,Object>> list = markService.getAllAssessItemByDeptId(departmentId);
        return list;
    }

    @RequestMapping(value = "/getAllMarkInfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON getAllMarkInfo(HttpServletRequest request, Integer limit, Integer page, String studentName,Integer tState) throws ParseException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        Integer managerId = manager.getManagerId();
        //获取所有学生
        List<Map<String,Object>> list1 = massessService.getAllMassessList(managerId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        /*天数差*/
        for (Map map : list1){
            String data1 = (String)map.get("hiredate");
            Date fromDate1 = df.parse(data1);
            Date toDate1 = df.parse(df.format(new Date()));
            long from1 =  fromDate1.getTime();
            long to1 = toDate1.getTime();
            int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
            if (days>90&&days<100){//转正
                Integer studentId = (Integer)map.get("studentId");
                Integer state = 1;
                massessService.editMassessState(studentId,state);
                Massess massess = massessService.getMassess(studentId,state);
                if (massess==null){
                    massessService.addMassessNone(studentId,state);
                }
            }else if (days>365&&days<375){//一年
                Integer studentId = (Integer)map.get("studentId");
                Integer state = 2;
                massessService.editMassessState(studentId,state);
            }else if(days>730&&days<740){//两年
                Integer studentId = (Integer)map.get("studentId");
                Integer state = 3;
                massessService.editMassessState(studentId,state);
            }else if(days>1095&&days<1195){//三年
                Integer studentId = (Integer)map.get("studentId");
                Integer state = 4;
                massessService.editMassessState(studentId,state);
            }else {
            }
        }
        //存放封装的学生和评分信息
        List<Map<String,Object>> list = new ArrayList<>();
        Integer count = limit*(page - 1);
        //某个部门的的全部员工
        List<Student> students = markService.getAllStudent(managerId,studentName,tState);
        List<Student> students1 = markService.getStudentPaging(managerId,count,limit,studentName,tState);
        //把每一个员工的信息和对应成绩进行封装
        for (Student student:students1){
            //每个员工的各评分项的成绩
            List<Map<String,Object>> marks = markService.getMarkByStuId(student.getStudentId(),student.getDepartmentId(),student.gettState());
            //把每个员工信息和他的各评分项的成绩储在map中
            Map<String,Object> map = new HashMap<>();
            map.put("studentId",student.getStudentId());
            map.put("studentName",student.getStudentName());
            for (Map mark:marks){
                if(mark.get("mark")==null){
                    map.put(  Integer.toString((Integer)mark.get("assessItemId")),"待评价");
                }else {
                    map.put(  Integer.toString((Integer)mark.get("assessItemId")),mark.get("mark"));
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

    @RequestMapping(value = "/getDetailById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSON  getDetailById(Integer studentId,Integer tState, HttpSession session){
        //存放封装的员工和评分信息
        List<Map<String,Object>> list = new ArrayList<>();
        //评价人
        User user = (User) session.getAttribute("user");
        //员工评价
        Massess massess = massessService.getMassess(studentId,tState);
        Map<String,Object> stu = studentService.getStuInfoById(studentId);
        //每个员工的各评分项的成绩
        List<Map<String,Object>> marks = markService.getMarkByStuId((Integer) stu.get("studentId"),(Integer) stu.get("departmentId"),tState);
        //把每个员工信息和他的各评分项的成绩存储在map中
        Map<String,Object> map = new HashMap<>();
        map.put("project","工作评价");
        map.put("department",stu.get("departmentName"));
        map.put("job",stu.get("jobName"));
        map.put("manager",user.getUserName());
        for (Map mark:marks){
            if(mark.get("mark")==null){
                map.put(  Integer.toString((Integer)mark.get("assessItemId")),"待评价");
            }else {
                map.put(  Integer.toString((Integer)mark.get("assessItemId")),mark.get("mark"));
            }
        }
        if(massess == null || massess.getEvaluate() == null){
            map.put("evaluate"," ");
        }else {
            map.put("evaluate",massess.getEvaluate());
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
