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

    /**
     * 编辑评分项
     * @param assessItem 评分项
     * @return 评分项编辑信息
     */
    @RequestMapping(value = "/editAssessItem",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editAssessItem(AssessItem assessItem){
        //修改评分项
        boolean isEdit = assessItemService.editAssessItem(assessItem);
        if (isEdit){//修改成功
            return "编辑成功";
        }else {//修改失败
            return "编辑失败";
        }
    }

    /**
     * 根据评分项id获取评分项信息，并跳转到编辑评分项页面
     * @param assessItemId 评分项id
     * @param model 实体类
     * @return 评分项页面
     */
    @RequestMapping(value = "/getAssessItemById",produces = "text/html;charset=utf-8")
    public String getAssessItemById(Integer assessItemId, Model model){
        //根据id获取要编辑的评分项
        AssessItem assessItem = assessItemService.getAssessItemById(assessItemId);
        //将获取的评分项存入请求中
        model.addAttribute("assessItem",assessItem);
        //跳转到编辑评分项页面
        return "editAssessItem";
    }

    /**
     * 新增评分项
     * @param assessItem 评分项
     * @return 新增评分项提示
     */
    @RequestMapping(value = "/addAssessItem",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addAssessItem(AssessItem assessItem){
        //新增评分项
        boolean isAdd = assessItemService.addAssessItem(assessItem);
        if (isAdd){//新增成功
            return "新增成功";
        }else {//新增失败
            return "新增失败";
        }
    }

    /**
     * 根据评分项名称查询评分项
     * @param assessItemName 评分项名称
     * @return 查询结果
     */
    @RequestMapping(value = "getAssessItemByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAssessItemByName(String assessItemName){
        //根据评分项名称获取评分项信息，判断该评分项名称是否已存在
        AssessItem assessItem = assessItemService.getAssessItemByName(assessItemName);
        if (assessItem != null){//如果该评分项名称已存在
            String str = assessItem.getAssessItemId() + "";
            return str;
        }else {//该评分项名称不存在
            return "n";
        }
    }

    /**
     * 获取新增评分项页面
     * @return 新增评分项页面
     */
    @RequestMapping("getAssessItemAdd")
    public String getAssessItemAdd(){
        return "addAssessItem";
    }

    /**
     * 根据评分项id删除评分项
     * @param assessItemId 评分项id
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteAssessItemById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteAssessItemById(Integer assessItemId){
        //删除评分项
        boolean isDelete = assessItemService.deleteAssessItemById(assessItemId);
        if (isDelete){//删除成功
            return "删除成功";
        }else {//删除失败
            return "删除失败";
        }
    }

    /**
     * 获取评分项列表
     * @param request 请求
     * @return 评分项列表
     */
    @RequestMapping(value = "/getAllAssessItem",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllAssessItem(HttpServletRequest request){
        //获取过滤条件
        String assessItemName = request.getParameter("assessItemName");
        //获取过滤后的评价项列表
        List<AssessItem> list = assessItemService.getAllAssessItem(assessItemName);

        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;//需要跳过的条目数
        //获取过滤和分页后的评价项列表
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

    /**
     * 获取评分项列表页面
     * @return 评分项列表页面
     */
    @RequestMapping("getAssessItemList")
    public String getAssessItemList(){
        //跳转到评分项页面
        return "assessItemList";
    }
}
