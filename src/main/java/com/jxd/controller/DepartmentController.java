package com.jxd.controller;

import com.jxd.model.Department;
import com.jxd.model.Student;
import com.jxd.service.IDepartmentService;
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
 * @Date 2020/9/12 17:13
 * @Version 1.0
 */
@Controller
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    /**
     * 编辑部门
     * @param department 要编辑的部门
     * @return 编辑结论
     */
    @RequestMapping(value = "/editDepartment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editDepartment(Department department){
        //修改部门信息
        boolean isEdit = departmentService.editDepartment(department);
        if (isEdit){//修改成功
            return "编辑成功";
        }else {//修改失败
            return "编辑失败";
        }
    }

    /**
     * 根据部门id获取部门，并返回编辑页面
     * @param departmentId 部门id
     * @param model 实体类
     * @return 编辑部门页面
     */
    @RequestMapping(value = "/getDepartmentById",produces = "text/html;charset=utf-8")
    public String getDepartmentById(Integer departmentId, Model model){
        //根据id获取要编辑的部门
        Department department = departmentService.getDepartmentById(departmentId);
        //将获取的部门信息存入请求
        model.addAttribute("department",department);
        //跳转到编辑部门页面
        return "editDepartment";
    }

    /**
     * 新增部门
     * @param department 要新增的部门
     * @return 新增结论
     */
    @RequestMapping(value = "/addDepartment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addDepartment(Department department){
        //新增部门
        boolean isAdd = departmentService.addDepartment(department);
        if (isAdd){//新增成功
            return "新增成功";
        }else {//新增失败
            return "新增失败";
        }
    }

    /**
     * 根据部门名称查询部门
     * @param departmentName 部门名称
     * @return 查询结果
     */
    @RequestMapping(value = "getDepartmentByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getDepartmentByName(String departmentName){
        //根据部门名称查询部门
        Department department = departmentService.getDepartmentByName(departmentName);
        if (department != null){//部门名称已存在
            String str = department.getDepartmentId() + "";
            return str;
        }else {//部门名称不存在
            return "n";
        }
    }

    /**
     * 获取新增部门页面
     * @return 新增部门页面
     */
    @RequestMapping("getDepartmentAdd")
    public String getDepartmentAdd(){
        //跳转到新增部门页面
        return "addDepartment";
    }

    /**
     * 根据部门id删除部门
     * @param departmentId 部门id
     * @return 删除结论
     */
    @RequestMapping(value = "/deleteDepartmentById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteDepartmentById(Integer departmentId){
        //根据部门id查询学员
        List<Student> students = departmentService.getStudentByDepartmentId(departmentId);
        //若没有学员
        if (students.size() == 0){
            //删除该部门
            boolean isDelete = departmentService.deleteDepartmentById(departmentId);
            if (isDelete){//删除成功
                return "删除成功";
            }else {//删除失败
                return "删除失败";
            }
        }else {//若部门下有学员，提示不能删除
            return "该部门中有学员，不能删除";
        }
    }

    /**
     * 获取部门列表
     * @param request 请求
     * @return 部门列表
     */
    @RequestMapping(value = "/getAllDepartment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllDepartment(HttpServletRequest request){
        //过滤条件
        String departmentName = request.getParameter("departmentName");
        //获取过滤后的所有部门
        List<Department> list = departmentService.getAllDepartment(departmentName);

        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;//需要跳过的条目数
        //获取过滤后的分页评价项
        List<Department> list1 = departmentService.getDepartmentPaging(count,pageSize,departmentName);
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
     * 获取部门列表显示页面
     * @return 部门列表显示页面
     */
    @RequestMapping("getDepartmentList")
    public String getDepartmentList(){
        //跳转到部门列表页面
        return "departmentList";
    }
}
