package com.jxd.controller;

import com.jxd.model.*;
import com.jxd.service.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    IScoreService scoreService;
    @Autowired
    ISassessService sassessService;
    @Autowired
    IMassessService massessService;
    @Autowired
    IMarkService markService;

    @RequestMapping("/getStudentListByClassId")
    @ResponseBody
    public JSON getStudentListByClassId(Integer classId,Integer managerId,Integer limit,Integer page){
        List<Map<String,Object>> studentList = new ArrayList<>();
        int count = (page - 1) * limit;
        //获取该班级的所有学员
        List<Student> list = studentService.getStudentListByClassId(classId,managerId);
        //分页获取该班级的所有学员
        List<Student> list1 = studentService.getStudentListByClassIdPaging(classId,managerId,count,limit);
        //获取该班期课程列表
        List<Map<String,Object>> courseList = scoreService.getAllCourseByClassId(classId);
        //获取经理评价的评价阶段
        List<TState> tStateList = studentService.getTStateList();
        //把每一个学生的信息和对应评分进行封装
        for (Student student:list1){
            //获取该学生的学校各课程成绩
            List<Map<String,Object>> scores = scoreService.getScoreByStuId(student.getStudentId(),student.getClassId());
            //获取该学员的学校整体评价分数
            Sassess sassess = sassessService.getSassessByStuId(student.getStudentId());
            //获取该学员的项目经理整体评价分数
            List<Massess> massesses = studentService.getMassessByStuId(student.getStudentId());
            //把每个学生信息和他的各科成绩存储在map中
            Map<String,Object> map = new HashMap<>();
            map.put("studentId",student.getStudentId());
            map.put("studentName",student.getStudentName());
            map.put("sex",student.getSex());
            map.put("graduate",student.getGraduate());
            map.put("homeTown",student.getHomeTown());
            //添加学校课程评分
            if(scores.size() == 0){
                for (int i = 0;i < courseList.size(); i++){
                    map.put(courseList.get(i).get("courseId").toString(),"<div style=\"color:#9c9c9c\">待评分</div>");
                }
            }else if (scores.size() == courseList.size()){
                for (int i = 0;i < scores.size(); i++){
                    if (scores.get(i).get("score") == null){
                        map.put(courseList.get(i).get("courseId").toString(),"<div style=\"color:#9c9c9c\">待评分</div>");
                    }else {
                        map.put(courseList.get(i).get("courseId").toString(),scores.get(i).get("score"));
                    }
                }
            }else {
                for (int i = 0;i < scores.size(); i++){
                    if (scores.get(i).get("score") == null){
                        map.put(courseList.get(i).get("courseId").toString(),"<div style=\"color:#9c9c9c\">待评分</div>");
                    }else {
                        map.put(courseList.get(i).get("courseId").toString(),scores.get(i).get("score"));
                    }
                }
                for (int i = scores.size(); i < courseList.size(); i++){
                    map.put(courseList.get(i).get("courseId").toString(),"<div style=\"color:#9c9c9c\">待评分</div>");
                }
            }
            //添加学校整体评价评分
            if (sassess == null){
                map.put("sevaluate","<div style=\"color:#9c9c9c\">待评分</div>");
            }else {
                map.put("sevaluate",sassess.getEvaluate());
            }
            //添加项目经理整体评价评分
            if(massesses.size() == 0){
                for (int i = 0;i < tStateList.size(); i++){
                    map.put("mevaluate" + (i + 1),"<div style=\"color:#9c9c9c\">待评分</div>");
                }
            }else if (massesses.size() == tStateList.size()){
                for (int i = 0;i < massesses.size(); i++){
                    if (massesses.get(i).getEvaluate() == null){
                        map.put("mevaluate" + (i + 1),"<div style=\"color:#9c9c9c\">待评分</div>");
                    }else {
                        map.put("mevaluate" + (i + 1),massesses.get(i).getEvaluate());
                    }
                }
            }else {
                for (int i = 0;i < massesses.size(); i++){
                    if (massesses.get(i).getEvaluate() == null){
                        map.put("mevaluate" + (i + 1),"<div style=\"color:#9c9c9c\">待评分</div>");
                    }else {
                        map.put("mevaluate" + (i + 1),massesses.get(i).getEvaluate());
                    }
                }
                for (int i = massesses.size(); i < tStateList.size(); i++){
                    map.put("mevaluate" + (i + 1),"<div style=\"color:#9c9c9c\">待评分</div>");
                }
            }
            //把map添加到list中
            studentList.add(map);
        }
        //转换为json数组
        JSONArray jsonArray = JSONArray.fromObject(studentList);
        //创建json对象，用于返回layui表格需要的数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/getCourseByClassId")
    @ResponseBody
    public List<Map<String, Object>> getCourseByClassId(Integer classId){
        //根据班期id获取班期的所学课程
        List<Map<String,Object>> list = scoreService.getAllCourseByClassId(classId);
        return list;
    }

    @RequestMapping("/allDetailed")
    public String allDetailed(Integer studentId, Model model){
//        Sassess sassess = sassessService.getSassessByStuId(studentId);
//        model.addAttribute("sassess",sassess);
//        model.addAttribute("studentId",studentId);
        Massess massess1 = massessService.getMassess(studentId,1);
        model.addAttribute("massess1",massess1);
        Massess massess2 = massessService.getMassess(studentId,2);
        model.addAttribute("massess2",massess2);
        Massess massess3 = massessService.getMassess(studentId,3);
        model.addAttribute("massess3",massess3);
        Massess massess4 = massessService.getMassess(studentId,4);
        model.addAttribute("massess4",massess4);
        model.addAttribute("studentId",studentId);
        return "allDetailed";
    }

    @RequestMapping(value = "/getStudentTracking",produces = "text/html;charset=utf-8")
    public String getStudentTracking(HttpServletRequest request,Model model){
        //获取各个下拉列表的值，并存入session
        List<Clazz> clazzList = studentService.getClazzList();
        List<User> managerList = studentService.getManagerList();
        List<Department> departmentList = departmentService.getAllDepartment(null);
        List<Job> jobList = jobService.getAllJob(null);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        if (manager==null){
            model.addAttribute("managerId",0);
        }else {
            model.addAttribute("managerId",manager.getManagerId());
        }
        model.addAttribute("clazzList",clazzList);
        model.addAttribute("managerList",managerList);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("jobList",jobList);
        model.addAttribute("clazzId",clazzList.get(0).getClassId());
        return "studentTracking";
    }

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
        return "editStudent";
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
        return "addStudent";
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
    public JSON getAllStudent(HttpServletRequest request, HttpSession session){
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

    @RequestMapping(value = "/getAllStudentByTid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllStudentByTid(HttpServletRequest request, HttpSession session){
        //过滤条件
        String studentName = request.getParameter("studentName");
        String departmentId = request.getParameter("departmentId");
        String jobId = request.getParameter("jobId");
        User user = (User) session.getAttribute("user");
        String teacherName = user.getUserName();
        //获取所有课程
        List<Student> list = studentService.getAllStudentByTid(studentName,departmentId,jobId,teacherName);
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页课程
        List<Map<String,Object>> list1 = studentService.getAllStudentByTidPaging(count,pageSize,studentName,departmentId,jobId,teacherName);
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
        return "studentList";
    }

    @RequestMapping("/tStudentList")
    public String tStudentList(Model model){
        List<Department> departmentList = departmentService.getAllDepartment(null);
        List<Job> jobList = jobService.getAllJob(null);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("jobList",jobList);
        return "tStudentList";
    }


    @RequestMapping("/mStudentList")
    public String mStudentList(HttpServletRequest request,Model model){
        List<Clazz> clazzList = studentService.getClazzList();
        List<User> managerList = studentService.getManagerList();
        List<Job> jobList = jobService.getAllJob(null);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Manager manager = markService.getDepatermentId(user.getUserId());
        model.addAttribute("clazzList",clazzList);
        model.addAttribute("managerList",managerList);
        model.addAttribute("jobList",jobList);
        model.addAttribute("managerId",manager.getManagerId());
        return "mStudentList";
    }
}
