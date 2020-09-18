package com.jxd.controller;

import com.jxd.model.Teacher;
import com.jxd.model.User;
import com.jxd.service.ITeacherService;
import com.jxd.service.IUserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    IUserService userService;
@Autowired
    ITeacherService teacherService;
    @RequestMapping("/teacherList")
    @ModelAttribute
    public String teacherList(Model model) {
        return "teacherList";
    }

    @RequestMapping("/addTeacher")
    @ModelAttribute
    public String addManager(Model model) {
        return "addTeacher";
    }
    @RequestMapping("/editTeacher")
    @ModelAttribute
    public String editTeacher(Model model,String teacherId) {
        List<Teacher>list=teacherService.getAllTeacherById(Integer.parseInt(teacherId));
        String s1="";
        String s2="";
        String s3="";
        String s4="";
        String s5="";
        String s6="";
        String s7="";
        String s8="";
        String s9="";
        String s10="";
        for (Teacher m:list){
            s1= String.valueOf(m.getTeacherId());

        }
        for (Teacher m:list){
            s2= String.valueOf(m.getTeacherId());

        }
        for (Teacher m:list){
            s3=m.getTeacherName();

        }
        for (Teacher m:list){
            s5=m.getBirthday();

        }
        for (Teacher m:list){
            s6=m.getHomeTown();

        }
        for (Teacher m:list){
            s7=m.getIdCardNum();

        }
        for (Teacher m:list){
            s8=m.getPhoneNumber();

        }
        for (Teacher m:list){
            s9=m.getPhoto();

        }
        for (Teacher m:list){
            s10=m.getSex();

        }
        model.addAttribute("teacherId", s1);
        model.addAttribute("userId", s2);
        model.addAttribute("teacherName", s3);
        model.addAttribute("birthday", s5);
        model.addAttribute("homeTown", s6);
        model.addAttribute("idCardNum", s7);
        model.addAttribute("phoneNumber", s8);
        model.addAttribute("photo", s9);
        model.addAttribute("sex", s10);
        return "editTeacher";
    }
    @RequestMapping("/teacherDetail")
    @ModelAttribute
    public String teacherDetail(Model model,String teacherId) {
        List<Teacher>list=teacherService.getAllTeacherById(Integer.parseInt(teacherId));
        String s1="";
        String s2="";
        String s3="";
        String s4="";
        String s5="";
        String s6="";
        String s7="";
        String s8="";
        String s9="";
        String s10="";
        for (Teacher m:list){
            s1= String.valueOf(m.getTeacherId());

        }
        for (Teacher m:list){
            s2= String.valueOf(m.getTeacherId());

        }
        for (Teacher m:list){
            s3=m.getTeacherName();

        }
        for (Teacher m:list){
            s5=m.getBirthday();

        }
        for (Teacher m:list){
            s6=m.getHomeTown();

        }
        for (Teacher m:list){
            s7=m.getIdCardNum();

        }
        for (Teacher m:list){
            s8=m.getPhoneNumber();

        }
        for (Teacher m:list){
            s9=m.getPhoto();

        }
        for (Teacher m:list){
            s10=m.getSex();

        }
        model.addAttribute("teacherId", s1);
        model.addAttribute("userId", s2);
        model.addAttribute("teacherName", s3);
        model.addAttribute("birthday", s5);
        model.addAttribute("homeTown", s6);
        model.addAttribute("idCardNum", s7);
        model.addAttribute("phoneNumber", s8);
        model.addAttribute("photo", s9);
        model.addAttribute("sex", s10);
        return "teacherDetail";
    }
    @RequestMapping(value = "/addNewTeacher",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewTeacher(String teacherName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        Teacher teacher = new Teacher(teacherName, birthday, idCardNum, phoneNumber, sex, homeTown, photo);
        Integer mid = null;
        boolean isAdd = teacherService.addTeacher(teacher);
        List<Teacher> list1 = teacherService.getMaxId();
        if (isAdd) {
            Integer role = 2;
            String pwd = "123456";
            boolean addUser = userService.addUser(teacherName, pwd, role);
            List<User> list = userService.getMaxUserId();
            for (User u : list) {
                for (Teacher m : list1) {
                    boolean isupdate = teacherService.updateUserId(u.getUserId(), m.getTeacherId());
                }
            }
            return "新增成功";
        } else {
            return "新增失败";
        }
    }
    @RequestMapping(value = "/editTheTeacher",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editTheTeacher(String teacherId,String teacherName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        Integer teacherId1=Integer.parseInt(teacherId);
        Teacher teacher = new Teacher(teacherId1,teacherName, birthday, idCardNum, phoneNumber, sex, homeTown, photo);
        boolean isUpdate = teacherService.updateTeacher(teacher);
        if (isUpdate) {


            return "修改成功";
        } else {
            return "修改失败";
        }
    }
    @RequestMapping(value = "/getAllTeacher", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllTeacher(String limit, String page) {
        //过滤条件
        //获取所有部门
        Integer pageSize = Integer.parseInt(limit);
        Integer pageIndex = Integer.parseInt(page);
        List<Teacher> list = teacherService.getAllTeacher();
        //获取分页数据
        pageIndex = (pageIndex - 1) * pageSize;

//        //获取分页评价项
        List<Teacher> list1 = teacherService.getAllTeacherByPage(pageIndex, pageSize);
//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/delTeacherById",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delTeacherById(String teacherIds) {

        String[] split = teacherIds.split(",");
        boolean isdel = false;
        Integer id = null;
        List<Teacher> list = new ArrayList<>();
        for (String mid : split) {
            id = Integer.parseInt(mid);
            list = teacherService.getTeacherById(id);
            isdel = teacherService.delTeacherById(Integer.parseInt(mid));
        }
        if (isdel) {
            for (Teacher m : list) {
                Integer userId = m.getUserId();
                boolean delUser = userService.deleteById(userId);
            }
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getTeachers", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getTeachers(String teacherName) {
        //过滤条件
        //获取所有部门
        List<Teacher> list = teacherService.getTeachers(teacherName);
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
