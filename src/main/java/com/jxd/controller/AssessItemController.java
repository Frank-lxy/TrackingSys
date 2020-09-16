package com.jxd.controller;

import com.jxd.model.AssessItem;
import com.jxd.service.IAssessItemService;
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
 * @Description 评价项控制
 * @Author Song SaiSai
 * @Date 2020/9/12 15:31
 * @Version 1.0
 */
@Controller
public class AssessItemController {
    @Autowired
    IAssessItemService assessItemService;

    @RequestMapping(value = "/editAssessItem",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editAssessItem(AssessItem assessItem){
        boolean isEdit = assessItemService.editAssessItem(assessItem);
        if (isEdit){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    @RequestMapping(value = "/getAssessItemById",produces = "text/html;charset=utf-8")
    public String getAssessItemById(Integer assessItemId, Model model){
        AssessItem assessItem = assessItemService.getAssessItemById(assessItemId);
        model.addAttribute("assessItem",assessItem);
        return "assessItemEdit";
    }

    @RequestMapping(value = "/addAssessItem",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addAssessItem(AssessItem assessItem){
        boolean isAdd = assessItemService.addAssessItem(assessItem);
        if (isAdd){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "getAssessItemByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAssessItemByName(String assessItemName){
        AssessItem assessItem = assessItemService.getAssessItemByName(assessItemName);
        if (assessItem != null){
            return "y";
        }else {
            return "n";
        }
    }

    @RequestMapping("getAssessItemAdd")
    public String getAssessItemAdd(){
        return "assessItemAdd";
    }

    @RequestMapping(value = "/deleteAssessItemById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteAssessItemById(Integer assessItemId){
        boolean isDelete = assessItemService.deleteAssessItemById(assessItemId);
        if (isDelete){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getAllAssessItem",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllAssessItem(HttpServletRequest request){
        //过滤条件
        String assessItemName = request.getParameter("assessItemName");
        //获取所有评价项
        List<AssessItem> list = assessItemService.getAllAssessItem(assessItemName);
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页评价项
        List<AssessItem> list1 = assessItemService.getAssessItemPaging(count,pageSize,assessItemName);
        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());//一共有多少条数据
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @RequestMapping("getAssessItemList")
    public String getAssessItemList(){
        return "assessItemList";
    }
}
