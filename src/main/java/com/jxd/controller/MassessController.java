package com.jxd.controller;

import com.jxd.model.Manager;
import com.jxd.model.Massess;
import com.jxd.model.Student;
import com.jxd.model.User;
import com.jxd.service.IMarkService;
import com.jxd.service.IMassessService;
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
 * @date : 2020-09-12 11:15
 **/
@Controller
public class MassessController {
    @Autowired
    IMassessService massessService;
    @Autowired
    IMarkService markService;

    /**
     * 评价的跳转页面
     * @param model 模板
     * @return 转正时期的评价的页面
     */
    @RequestMapping("/massessList")
    public String massessList(Model model){
        model.addAttribute("tState",1);
        return "massessList";
    }

    /**
     * 评价的跳转页面
     * @param model 模板
     * @return 一年时期的评价的页面
     */
    @RequestMapping("/massessList1")
    public String massessList1(Model model){
        model.addAttribute("tState",2);
        return "massessList";
    }

    /**
     * 评价的跳转页面
     * @param model 模板
     * @return 两年时期的评价的页面
     */
    @RequestMapping("/massessList2")
    public String massessList2(Model model){
        model.addAttribute("tState",3);
        return "massessList";
    }

    /**
     * 评价的跳转页面
     * @param model 模板
     * @return 三年时期的评价的页面
     */
    @RequestMapping("/massessList3")
    public String massessList3(Model model){
        model.addAttribute("tState",4);
        return "massessList";
    }

    /**
     * 所有学生的评价数据
     * @param request 接收请求
     * @param managerId 学生id
     * @return 所有学生的评价数据
     */
    @RequestMapping(value = "/getAllMStudent",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getMAllStudent(HttpServletRequest request,Integer managerId){
        //过滤条件
        String studentName = request.getParameter("studentName");
        String jobId = request.getParameter("jobId");
        //获取所有课程
        List<Student> list = massessService.getAllMStudent(studentName,jobId,managerId);
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页课程
        List<Map<String,Object>> list1 = massessService.getMStudentPaging(count,pageSize,studentName,jobId,managerId);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    /**
     * 修改评价
     * @param massess 评价对象
     * @return 是否修改成功
     */
    @RequestMapping(value = "/editMassess",produces = "text/html;charset=utf-8")
    @ResponseBody//针对ajax操作，将响应内容添加至响应流
    public String editMassess(Massess massess){
        boolean isEdit = massessService.editMassess(massess);
        if(isEdit){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    /**
     * 修改页面的跳转
     * @param maId 评价id
     * @param model 模板
     * @return 修改页面
     */
    @RequestMapping("/getMassessById")
    public String getMassessById(Integer maId, Model model){
        Massess massess = massessService.getMassessById(maId);
        Integer studentId = massess.getStudentId();
        Student student = massessService.getStudentById(studentId);
        model.addAttribute("student",student);
        model.addAttribute("massess",massess);
        return "editMassess";
    }

    /**
     * 添加评价
     * @param massess 评价对象
     * @return 是否添加成功
     */
    @RequestMapping(value = "/addMassess",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addMassess(Massess massess){
        boolean isDel = massessService.delMassessById(massess);
        boolean isAdd = massessService.addMassess(massess);
        boolean isAdd1 = massessService.addMassessNone(massess.getStudentId(),massess.gettState()+1);

        if(isAdd&&isDel&&isAdd1){
            return "评价成功";
        }else {
            return "评价失败";
        }
    }

    /**
     * 添加页面跳转
     * @param studentId 学生id
     * @param model 模板
     * @return 跳转到添加评价页面
     */
    @RequestMapping("/addMassessPage")
    public String addMassessPage(Integer studentId, Model model){
        Student student = massessService.getStudentById(studentId);
        model.addAttribute("student",student);
        return "addMassess";
    }

    /**
     * 获得不同入职日期的所有学生的评价
     * @param request 接收请求
     * @param tState 员工入职状态
     * @return 所有学生不同入职状态的评价数据
     * @throws ParseException
     */
    @RequestMapping("/getAllMassess")
    @ResponseBody
    public JSONObject getAllMassess(HttpServletRequest request,Integer tState) throws ParseException {
        //过滤条件
        String studentName = request.getParameter("studentName");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        Integer managerId = manager.getManagerId();
        //获取所有学生
        List<Map<String,Object>> list = massessService.getAllMassessList(managerId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //入职日期与当前日期的天数差
        for (Map map : list){
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
        //评价表数据
        List<Map<String,Object>> list2 = massessService.getMassessList(studentName,managerId,tState);
        //获取分页数据
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        Integer pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        Integer count = (pageIndex - 1) * pageSize;
        List<Map<String,Object>> list1 = massessService.getMassessLists(count,pageSize,studentName,managerId,tState);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1.toArray());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list2.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
}
