package com.jxd.controller;

import com.jxd.model.Sassess;
import com.jxd.service.ISassessService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author:Chai HongFang
 * @Description:
 * @Date:2020/9/12 10:39
 * @Version:1.0
 */
@Controller
public class SassessController {
    @Autowired
    ISassessService sassessService;

    @RequestMapping(value = "/getAssessByPage",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getAssessByPage(String studentName,Integer classId,Integer limit, Integer page){
        Integer count = limit*(page - 1);
        List<Map<String,Object>> list = sassessService.getAssessByPage(studentName,classId,count,limit);
        List<Map<String,Object>> list1 = sassessService.getAllAssess();
        //把list返回到empList页面
        JSONArray jsonArray = JSONArray.fromObject(list);
        //创建json对象，用于返回layui表格需要的数据

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list1.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);

        return jsonObject;
    }

    @RequestMapping("/sassess")
    public String sassessPage(Integer studentId,Integer classId,String studentName,Model model){
        model.addAttribute("studentId",studentId);
        model.addAttribute("classId",classId);
        model.addAttribute("studentName",studentName);
        return "addSassess";
    }

    @RequestMapping(value = "/addSassess",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String addSassess(Sassess sassess){
        boolean isAdd = sassessService.addSassess(sassess);
        if (isAdd){
            return "评价成功";
        }else {
            return "评价失败";
        }
    }
    @RequestMapping("/getSassessById")
    public String getSassessById(Integer saId, String studentName,Model model){
        Sassess sassess = sassessService.getSassessById(saId);
        model.addAttribute("studentName",studentName);
        model.addAttribute("sassess",sassess);
        return "editSassess";
    }

    @RequestMapping(value = "/editSassess",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String editSassess(Sassess sassess){
        boolean isEdit = sassessService.editSassess(sassess);
        if (isEdit){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

}
