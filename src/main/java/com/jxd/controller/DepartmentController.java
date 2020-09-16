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

    @RequestMapping(value = "/editDepartment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editDepartment(Department department){
        boolean isEdit = departmentService.editDepartment(department);
        if (isEdit){
            return "编辑成功";
        }else {
            return "编辑失败";
        }
    }

    @RequestMapping(value = "/getDepartmentById",produces = "text/html;charset=utf-8")
    public String getDepartmentById(Integer departmentId, Model model){
        Department department = departmentService.getDepartmentById(departmentId);
        model.addAttribute("department",department);
        return "departmentEdit";
    }

    @RequestMapping(value = "/addDepartment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addDepartment(Department department){
        boolean isAdd = departmentService.addDepartment(department);
        if (isAdd){
            return "新增成功";
        }else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "getDepartmentByName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getDepartmentByName(String departmentName){
        Department department = departmentService.getDepartmentByName(departmentName);
        if (department != null){
            return "y";
        }else {
            return "n";
        }
    }

    @RequestMapping("getDepartmentAdd")
    public String getDepartmentAdd(){
        return "departmentAdd";
    }

    @RequestMapping(value = "/deleteDepartmentById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteDepartmentById(Integer departmentId){
        List<Student> students = departmentService.getStudentByDepartmentId(departmentId);
        if (students == null){
            boolean isDelete = departmentService.deleteDepartmentById(departmentId);
            if (isDelete){
                return "删除成功";
            }else {
                return "删除失败";
            }
        }else {
            return "该部门有学员存在，不能删除";
        }
    }

    @RequestMapping(value = "/getAllDepartment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllDepartment(HttpServletRequest request){
        //过滤条件
        String departmentName = request.getParameter("departmentName");
        //获取所有部门
        List<Department> list = departmentService.getAllDepartment();
        //获取分页数据
        int pageSize = Integer.parseInt(request.getParameter("limit"));//获取一页显示几条
        int pageIndex = Integer.parseInt(request.getParameter("page"));//获取当前页
        int count = (pageIndex - 1) * pageSize;
        //获取分页评价项
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

    @RequestMapping("getDepartmentList")
    public String getDepartmentList(){
        return "departmentList";
    }
}
