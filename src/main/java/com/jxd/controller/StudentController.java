package com.jxd.controller;

import com.jxd.model.*;
import com.jxd.service.IDepartmentService;
import com.jxd.service.IJobService;
import com.jxd.service.IStudentService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/13 9:36
 * @Version 1.0
 */
@Controller
@SessionAttributes({"clazzList","managerList","departmentList","jobList"})
public class StudentController {
    @Autowired
    IStudentService studentService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IJobService jobService;

    @RequestMapping(value = "/editStudent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editStudent(Student student){
        boolean isEdit = studentService.editStudent(student);
        if (isEdit){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    @RequestMapping(value = "/delFile",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String delFile(String path,HttpServletRequest request){
        //获取文件的完整路径
        String savePath = request.getServletContext().getRealPath("/" + path);
        //根据完整路径创建文件对象
        File file = new File(savePath);
        if (file.exists() && file.isFile()){
            if (file.delete()){
                return "删除成功";
            }else {
                return "删除失败";
            }
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getStudentById",produces = "text/html;charset=utf-8")
    public String getStudentById(Integer studentId, Model model){
        //根据学员编号获取学员基本信息
        Student student = studentService.getStudentById(studentId);
        //根据学员编号获取学员当前状态
        String state = studentService.getStateByStudentId(studentId);

        model.addAttribute("student",student);
        model.addAttribute("state",state);
        return "studentEdit";
    }

    @RequestMapping(value = "/addStudent",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addStudent(Student student){
        boolean isAdd = studentService.addStudent(student);
        if (isAdd){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("photo") MultipartFile multipartFile, HttpServletRequest request){
        //创建一个目录files，用于存放上传的照片
        String savePath = request.getServletContext().getRealPath("files");
        File file = new File(savePath);
        //如果file不存在或不是一个目录，我们创建它
        if (!file.exists() || !file.isDirectory()){
            file.mkdir();
        }
        //获取原文件名
        String oldName = multipartFile.getOriginalFilename();
        //对原文件名进行处理
        String newName = UUID.randomUUID() + "_" + oldName;
        //根据保存路径和新名字生成一个文件对象
        File saveFile = new File(savePath,newName);
        try {
            //将springMVC接收到的文件对象转换为普通的文件对象
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回文件保存路径即文件名
        return "files/" + newName;
    }

    @RequestMapping("getStudentAdd")
    public String getStudentAdd(){
        return "studentAdd";
    }

    @RequestMapping(value = "/deleteStudentById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStudentById(Integer studentId){
        boolean isDelete = studentService.deleteStudentById(studentId);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "deleteStudentBatch",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStudentBatch(String studentIds){
        boolean isDelete = studentService.deleteStudentBatch(studentIds);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getStudentDetailedById",produces = "text/html;charset=utf-8")
    public String getStudentDetailedById(Integer studentId, Model model){
        Map<String,Object> map = studentService.getStudentDetailedById(studentId);
        model.addAttribute("map",map);
        return "studentDetailed";
    }

    @RequestMapping(value = "/getAllStudent",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllStudent(HttpServletRequest request){
        //过滤条件
        String studentName = request.getParameter("studentName");
        String departmentId = request.getParameter("departmentId");
        String jobId = request.getParameter("jobId");
        //获取所有课程
        List<Student> list = studentService.getAllStudent(studentName,departmentId,jobId);
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页课程
        List<Map<String,Object>> list1 = studentService.getStudentPaging(count,pageSize,studentName,departmentId,jobId);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @RequestMapping("/getStudentList")
    public String getStudentList(Model model){
        List<Clazz> clazzList = studentService.getClazzList();
        List<User> managerList = studentService.getManagerList();
        List<Department> departmentList = departmentService.getAllDepartment(null);
        List<Job> jobList = jobService.getAllJob(null);
        model.addAttribute("clazzList",clazzList);
        model.addAttribute("managerList",managerList);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("jobList",jobList);
        return "studentList";
    }
}
