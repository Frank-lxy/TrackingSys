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

    /**
     * 编辑职务
     * @param job 要编辑的职务
     * @return 编辑结果
     */
    @RequestMapping(value = "/editJob",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editJob(Job job){
        //修改职务
        boolean isEdit = jobService.editJob(job);
        if (isEdit){//修改成功
            return "编辑成功";
        }else {//修改失败
            return "编辑失败";
        }
    }

    /**
     * 柑橘职务id查询职务并返回编辑页面
     * @param jobId 职务id
     * @param model 实体类
     * @return 编辑职务页面
     */
    @RequestMapping(value = "/getJobById",produces = "text/html;charset=utf-8")
    public String getJobById(Integer jobId, Model model){
        //根据id查询职务
        Job job = jobService.getJobById(jobId);
        //将查询到的职务存入请求
        model.addAttribute("job",job);
        //跳转到编辑职务页面
        return "editJob";
    }

    /**
     * 新增职务
     * @param job 要新增的职务
     * @return 新增结果
     */
    @RequestMapping(value = "/addJob",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addJob(Job job){
        //新增职务
        boolean isAdd = jobService.addJob(job);
        if (isAdd){//新增成功
            return "新增成功";
        }else {//新增失败
            return "新增失败";
        }
    }

    /**
     * 根据职务名称查询职务
     * @param jobName 职务名称
     * @return 查询结果
     */
    @RequestMapping(value = "getJobByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJobByName(String jobName){
        //根据职务名称获取职务
        Job job = jobService.getJobByName(jobName);
        if (job != null){//职务名称已存在
            String str = job.getJobId() + "";
            return str;
        }else {//职务名称不存在
            return "n";
        }
    }

    /**
     * 获取新增职务页面
     * @return 新增职务页面
     */
    @RequestMapping("getJobAdd")
    public String getJobAdd(){
        //跳转到新增职务页面
        return "addJob";
    }

    /**
     * 根据职务id删除职务
     * @param jobId 职务id
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteJobById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteJobById(Integer jobId){
        //根据id删除职务
        boolean isDelete = jobService.deleteJobById(jobId);
        if (isDelete){//删除成功
            return "删除成功";
        }else {//删除失败
            return "删除失败";
        }
    }

    /**
     * 获取职务列表
     * @param request 请求
     * @return 职务列表
     */
    @RequestMapping(value = "/getAllJob",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllJob(HttpServletRequest request){
        //过滤条件
        String jobName = request.getParameter("jobName");
        //获取过滤后的课程列表
        List<Job> list = jobService.getAllJob(jobName);

        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;//需要跳过的条目数
        //获取过滤后的分页课程
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

    /**
     * 获取职务列表显示页面
     * @return 职务列表显示页面
     */
    @RequestMapping("getJobList")
    public String getJobList(){
        //跳转到职务列表
        return "jobList";
    }
}
