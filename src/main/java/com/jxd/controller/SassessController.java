package com.jxd.controller;

import com.jxd.model.Sassess;
import com.jxd.model.User;
import com.jxd.service.ISassessService;
import com.jxd.service.IUserService;
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
    @Autowired
    IUserService userService;

    /**
     * 修改密码页面
     * @return editPwd网页
     */
    @RequestMapping("/editPwd")
    public String editPwd(){
        return "editPwd";
    }

    /**
     * 评价列表页面
     * @return sassessList网页
     */
    @RequestMapping("/sassessList")
    public String sassessList(){
        return "sassessList";
    }

    /**
     * 根据用户id修改密码
     * @param user 用户对象
     * @return 是否修改成功
     */
    @RequestMapping(value = "/editPwdById",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String editPwdById(User user){
        //修改密码是否成功
        boolean isEdit = userService.editPwdById(user);
        if (isEdit){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    /**
     * 分页获取评价
     * @param studentName 学生姓名
     * @param classId 班级id
     * @param limit 跳过的数量
     * @param page 获取数据的数量
     * @param userId 用户id
     * @return json数组
     */
    @RequestMapping(value = "/getAssessByPage",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getAssessByPage(String studentName,Integer classId,Integer limit, Integer page,Integer userId){
        Integer count = limit*(page - 1);
        User user = sassessService.getUserById(userId);
        //分页获得评价信息
        List<Map<String,Object>> list = sassessService.getAssessByPage(studentName,user.getUserName(),classId,count,limit);
        //获得评价信息
        List<Map<String,Object>> list1 = sassessService.getAllAssess(studentName,user.getUserName(),classId);

        //把list返回到assessList页面
        JSONArray jsonArray = JSONArray.fromObject(list);
        //创建json对象，用于返回layui表格需要的数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list1.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);

        return jsonObject;
    }

    /**
     * 增加评价页面
     * @param studentId 学生id
     * @param classId 班级id
     * @param studentName 班级姓名
     * @param model 模型
     * @return addSassess
     */
    @RequestMapping("/sassess")
    public String sassessPage(Integer studentId,Integer classId,String studentName,Model model){
        //增加评价时需要传递的参数
        model.addAttribute("studentId",studentId);
        model.addAttribute("classId",classId);
        //在页面上显示姓名
        model.addAttribute("studentName",studentName);
        return "addSassess";
    }

    /**
     * 增加评价
     * @param sassess 评价对象
     * @return 是否评价成功
     */
    @RequestMapping(value = "/addSassess",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String addSassess(Sassess sassess){
        //是否评价成功
        boolean isAdd = sassessService.addSassess(sassess);
        if (isAdd){
            return "评价成功";
        }else {
            return "评价失败";
        }
    }

    /**
     * 修改评价
     * @param saId 评价编号
     * @param studentName 学生姓名
     * @param model 模型
     * @return editSassess
     */
    @RequestMapping("/getSassessById")
    public String getSassessById(Integer saId, String studentName,Model model){
        //修改评价时得到原来的评价数据
        Sassess sassess = sassessService.getSassessById(saId);
        model.addAttribute("studentName",studentName);
        model.addAttribute("sassess",sassess);
        return "editSassess";
    }

    /**
     * 修改评价
     * @param sassess 评价对象
     * @return 是否修改成功
     */
    @RequestMapping(value = "/editSassess",produces ={ "text/html;charset=UTF-8"})
    @ResponseBody
    public String editSassess(Sassess sassess){
        //修改评价是否成功
        boolean isEdit = sassessService.editSassess(sassess);
        if (isEdit){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

}
