package com.jxd.controller;

import com.jxd.model.Job;
import com.jxd.service.IJobService;
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
 * @Description TODO
 * @Author Song SaiSai
 * @Date 2020/9/12 18:45
 * @Version 1.0
 */
@Controller
public class JobController {
    @Autowired
    IJobService jobService;

    @RequestMapping(value = "/editJob",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editJob(Job job){
        boolean isEdit = jobService.editJob(job);
        if (isEdit){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    @RequestMapping(value = "/getJobById",produces = "text/html;charset=utf-8")
    public String getJobById(Integer jobId, Model model){
        Job job = jobService.getJobById(jobId);
        model.addAttribute("job",job);
        return "jobEdit";
    }

    @RequestMapping(value = "/addJob",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addJob(Job job){
        boolean isAdd = jobService.addJob(job);
        if (isAdd){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "getJobByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJobByName(String jobName){
        Job job = jobService.getJobByName(jobName);
        if (job != null){
            return "y";
        }else {
            return "n";
        }
    }

    @RequestMapping("getJobAdd")
    public String getJobAdd(){
        return "jobAdd";
    }

    @RequestMapping(value = "/deleteJobById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteJobById(Integer jobId){
        boolean isDelete = jobService.deleteJobById(jobId);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getAllJob",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllJob(HttpServletRequest request){
        //过滤条件
        String jobName = request.getParameter("jobName");
        //获取所有课程
        List<Job> list = jobService.getAllJob();
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页课程
        List<Job> list1 = jobService.getJobPaging(count,pageSize,jobName);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @RequestMapping("getJobList")
    public String getJobList(){
        return "jobList";
    }
}
