package com.jxd.controller;

import com.jxd.model.Clazz;
import com.jxd.model.Course;
import com.jxd.model.Student;
import com.jxd.model.User;
import com.jxd.service.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ClazzController {
    @Autowired
    IClazzService clazzService;
    @Autowired
    IUserService userService;
    @Autowired
    ICourseService courseService;
    @Autowired
    ICourseselService courseselService;

    @RequestMapping(value = "/getAllClazz", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllClazz(String limit, String page) {
        //过滤条件
        //获取所有部门
        Integer pageSize = Integer.parseInt(limit);
        Integer pageIndex = Integer.parseInt(page);
        List<Clazz> list = clazzService.getAllClazz();
        //获取分页数据
        pageIndex = (pageIndex - 1) * pageSize;

//        //获取分页评价项
        List<Clazz> list1 = clazzService.getAllClazzByPage(pageIndex, pageSize);
//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping("/clazzList")
@ModelAttribute
    public String clazzList(Model model) {
        List<Clazz> list = clazzService.getAllClazz();//获取全部的班期
        List<String> list1=new ArrayList<>();
        for (Clazz s : list){
            String str=s.getClazz();
            list1.add(str);
        }
        model.addAttribute("clazz", list1);
        return "clazzList";
    }


    /**
     * 跳转到新增期次页面
     * @param model
     * @return
     */
    @RequestMapping("/addClazz")
    @ModelAttribute
    public String addClazz(Model model) {
        List<Clazz> list = clazzService.getMaxClazz();//获取ID最大的班期
        String num = null;
        String str1 = "";
        for (Clazz s : list) {
            str1 = s.getClazz();
        }

        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(str1);
        num = matcher.replaceAll("");
        Integer num2 = Integer.parseInt(num) + 1;
        String str2 = "金桥工程0" + num2 + "期";
        List<User> list1 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1 = userService.getAllTeachers();
        for (User u : list1) {
            String s = u.getUserName();
            list3.add(s);
        }
        List<Course> list2 = courseService.getAllCourse();
        for (Course c : list2) {
            String s = c.getCourseName();
            list4.add(s);
        }

        model.addAttribute("courseName", list4);
        model.addAttribute("teacherName", list3);
        model.addAttribute("clazz", str2);
        return "addClazz";
    }

    /**
     * 新增一个新的期次
     * @param clazz
     * @param courseName
     * @param teacherName
     * @return
     */
    @RequestMapping("/addNewClazz")
    @ResponseBody
    public String addNewClazz(String clazz, String courseName, String teacherName) {
        String []arr=courseName.split(",");
        List<String> list1 = new ArrayList<>();
        Integer classId = null;
        Integer courseId = null;
        boolean addCoursesel = false;
        boolean addClazz = clazzService.addClazz(clazz, teacherName);
        if (addClazz) {
            List<Clazz> list = clazzService.getAllClazzByName(clazz);
            for (Clazz c : list) {
                classId = c.getClassId();
            }
            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                List<Course> list2 = courseService.getCourseIdByName(s);
                for (Course c : list2) {
                    courseId = c.getCourseId();
                }
                addCoursesel = courseselService.addCoursesel(courseId, classId);
            }
        } else {
            return "新增班期失败";
        }
        if (addClazz && addCoursesel) {
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "/getClazz", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getClazz(String clazz,String teacherName) {
        //过滤条件
        //获取所有部门
        List<Clazz> list = clazzService.getClazz(clazz,teacherName);

//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

}
