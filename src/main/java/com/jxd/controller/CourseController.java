package com.jxd.controller;

import com.jxd.model.Course;
import com.jxd.service.ICourseService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 课程控制
 * @Author Song SaiSai
 * @Date 2020/9/12 11:18
 * @Version 1.0
 */
@Controller
public class CourseController {
    @Autowired
    ICourseService courseService;
    @RequestMapping(value = "/editCourse",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editCourse(Course course){
        boolean isEdit = courseService.editCourse(course);
        if (isEdit){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    @RequestMapping(value = "/getCourseById",produces = "text/html;charset=utf-8")
    public String getCourseById(Integer courseId, Model model){
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "editCourse";
    }

    @RequestMapping(value = "/addCourse",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addCourse(Course course){
        boolean isAdd = courseService.addCourse(course);
        if (isAdd){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "getCourseByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCourseByName(String courseName){
        Course course = courseService.getCourseByName(courseName);
        if (course != null){
            return "y";
        }else {
            return "n";
        }
    }

    @RequestMapping("getCourseAdd")
    public String getCourseAdd(){
        return "addCourse";
    }

    @RequestMapping(value = "/deleteCourseById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteCourseById(Integer courseId){
        boolean isDelete = courseService.deleteCourseById(courseId);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getAllCourse",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllCourse(HttpServletRequest request){
        //过滤条件
        String courseName = request.getParameter("courseName");
        //获取所有课程
        List<Course> list = courseService.getAllCourse(courseName);
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页课程
        List<Course> list1 = courseService.getCoursePaging(count,pageSize,courseName);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @RequestMapping("getCourseList")
    public String getCourseList(){
        return "courseList";
    }
}
