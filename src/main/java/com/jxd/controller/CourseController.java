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

    /**
     * 编辑课程
     * @param course 要编辑的课程
     * @return 结果
     */
    @RequestMapping(value = "/editCourse",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editCourse(Course course){
        //修改课程
        boolean isEdit = courseService.editCourse(course);
        if (isEdit){//修改成功
            return "编辑成功";
        }else {//修改失败
            return "编辑失败";
        }
    }

    /**
     * 根据课程id查询课程
     * @param courseId 课程id
     * @param model 实体类
     * @return 编辑课程页面
     */
    @RequestMapping(value = "/getCourseById",produces = "text/html;charset=utf-8")
    public String getCourseById(Integer courseId, Model model){
        //根据课程id查询课程
        Course course = courseService.getCourseById(courseId);
        //将查寻到的课程信息存入请求中
        model.addAttribute("course",course);
        //返回编辑课程页面
        return "editCourse";
    }

    /**
     * 新增课程
     * @param course 要新增的课程
     * @return 新增结果
     */
    @RequestMapping(value = "/addCourse",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addCourse(Course course){
        //将前台传过来的课程对象添加到数据库
        boolean isAdd = courseService.addCourse(course);
        if (isAdd){//添加成功
            return "新增成功";
        }else {//添加失败
            return "新增失败";
        }
    }

    /**
     * 根据课程名称获取课程
     * @param courseName 课程名称
     * @return 获取结果
     */
    @RequestMapping(value = "getCourseByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCourseByName(String courseName){
        //调用方法，根据课程名称查询课程，判断课程名称是否已经存在
        Course course = courseService.getCourseByName(courseName);
        if (course != null){//能查询到课程，表示课程名称已存在，返回课程id
            String str = course.getCourseId() + "";
            return str;
        }else {//不能查询到课程，表示课程名称不存在，返回n
            return "n";
        }
    }

    /**
     * 获取新增课程页面
     * @return 新增课程页面
     */
    @RequestMapping("getCourseAdd")
    public String getCourseAdd(){
        //返回新增课程页面
        return "addCourse";
    }

    /**
     * 根据课程id删除课程
     * @param courseId 课程id
     * @return 删除结论
     */
    @RequestMapping(value = "/deleteCourseById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteCourseById(Integer courseId){
        //调用方法根据课程id删除课程
        boolean isDelete = courseService.deleteCourseById(courseId);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 获取课程列表
     * @param request 请求
     * @return 课程列表
     */
    @RequestMapping(value = "/getAllCourse",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllCourse(HttpServletRequest request){
        //获得请求中的过滤条件
        String courseName = request.getParameter("courseName");
        //调用方法获取条件查询后的课程列表
        List<Course> list = courseService.getAllCourse(courseName);

        //获取分页相关数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;//要跳过的条目数
        //获取分页课程列表
        List<Course> list1 = courseService.getCoursePaging(count,pageSize,courseName);

        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);//data数据
        //返回json数据
        return jsonObject;
    }

    /**
     * 获取课程列表显示页面
     * @return 课程列表显示页面
     */
    @RequestMapping("getCourseList")
    public String getCourseList(){
        //返回课程列表页面
        return "courseList";
    }
}
