package com.jxd.controller;

import com.jxd.model.Department;
import com.jxd.model.Manager;
import com.jxd.model.User;
import com.jxd.service.IDepartmentService;
import com.jxd.service.IManagerService;
import com.jxd.service.IUserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ManagerController {
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IManagerService managerService;
    @Autowired
    IUserService userService;

    @RequestMapping("/managerList")
    @ModelAttribute
    public String managerList(Model model) {
        List<Department> list = departmentService.getAllDep();
        List<String> list1 = new ArrayList<>();
        for (Department d : list) {
            String s = d.getDepartmentName();
            list1.add(s);
        }
        model.addAttribute("department", list1);
        return "managerList";
    }

    @RequestMapping("/addManager")
    @ModelAttribute
    public String addManager(Model model) {
        List<Department> list = departmentService.getAllDep();
        List<String> list1 = new ArrayList<>();
        for (Department d : list) {
            String s = d.getDepartmentName();
            list1.add(s);
        }
        model.addAttribute("departmentName", list1);
        return "addManager";
    }
    @RequestMapping("/editManager")
    @ModelAttribute
    public String editManager(Model model,String managerId) {
    Manager manager = managerService.getManagerByManagerId(Integer.parseInt(managerId));
    String s1="";
        String s2="";
        String s3="";
        String s4="";
        String s5="";
        String s6="";
        String s7="";
        String s8="";
        String s9="";
        String s10="";
        s1 = String.valueOf(manager.getManagerId());
        s2 = String.valueOf(manager.getUserId());
        s3 = manager.getManagerName();
        s4 = manager.getDepartmentName();
        s5 = manager.getBirthday();
        s6 = manager.getHomeTown();
        s7 = manager.getIdCardNum();
        s8 = manager.getPhoneNumber();
        s9 = manager.getPhoto();
        s10 = manager.getSex();
        List<Department> list11 = departmentService.getAllDep();
        List<String> list12 = new ArrayList<>();
        for (Department d : list11) {
            String s = d.getDepartmentName();
            list12.add(s);
        }
        model.addAttribute("managerId", s1);
        model.addAttribute("userId", s2);
        model.addAttribute("managerName", s3);
        model.addAttribute("department", s4);
        model.addAttribute("birthday", s5);
        model.addAttribute("homeTown", s6);
        model.addAttribute("idCardNum", s7);
        model.addAttribute("phoneNumber", s8);
        model.addAttribute("photo", s9);
        model.addAttribute("sex", s10);
        model.addAttribute("departmentName", list12);
        return "editManager";
    }
    @RequestMapping("/managerDetail")
    @ModelAttribute
    public String managerDetail(Model model,String managerId) {
        Manager manager = managerService.getManagerByManagerId(Integer.parseInt(managerId));
        String s1="";
        String s2="";
        String s3="";
        String s4="";
        String s5="";
        String s6="";
        String s7="";
        String s8="";
        String s9="";
        String s10="";
        s1 = String.valueOf(manager.getManagerId());
        s2 = String.valueOf(manager.getUserId());
        s3 = manager.getManagerName();
        s4 = manager.getDepartmentName();
        s5 = manager.getBirthday();
        s6 = manager.getHomeTown();
        s7 = manager.getIdCardNum();
        s8 = manager.getPhoneNumber();
        s9 = manager.getPhoto();
        s10 = manager.getSex();
        List<Department> list11 = departmentService.getAllDep();
        List<String> list12 = new ArrayList<>();
        for (Department d : list11) {
            String s = d.getDepartmentName();
            list12.add(s);
        }
        model.addAttribute("managerId", s1);
        model.addAttribute("userId", s2);
        model.addAttribute("managerName", s3);
        model.addAttribute("department", s4);
        model.addAttribute("birthday", s5);
        model.addAttribute("homeTown", s6);
        model.addAttribute("idCardNum", s7);
        model.addAttribute("phoneNumber", s8);
        model.addAttribute("photo", s9);
        model.addAttribute("sex", s10);
        model.addAttribute("departmentName", list12);
        return "managerDetail";
    }
    @RequestMapping(value = "/addNewManager",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewManager(String managerName, String departmentName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        Manager manager = new Manager(managerName, departmentName, birthday, idCardNum, phoneNumber, sex, homeTown, photo);
        Integer mid = null;
        boolean isAdd = managerService.addManager(manager);
        List<Manager> list1 = managerService.getMaxId();
        if (isAdd) {
            Integer role = 3;
            String pwd = "123456";
            boolean addUser = userService.addUser(managerName, pwd, role);
            List<User> list = userService.getMaxUserId();
            for (User u : list) {
                for (Manager m : list1) {
                    boolean isupdate = managerService.updateUserId(u.getUserId(), m.getManagerId());
                }
            }
            return "新增成功";
        } else {
            return "新增失败";
        }
    }
    @RequestMapping(value = "/editTheManager",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editTheManager(String managerId,String managerName, String departmentName, String birthday, String idCardNum, String phoneNumber, String sex, String homeTown, String photo) {
        Integer managerId1=Integer.parseInt(managerId);
        Manager manager = new Manager(managerId1,managerName, departmentName, birthday, idCardNum, phoneNumber, sex, homeTown, photo);
        boolean isUpdate = managerService.updateManager(manager);
        if (isUpdate) {


            return "修改成功";
        } else {
            return "修改失败";
        }
    }
    @RequestMapping(value = "/getAllManager", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllManager(String limit, String page) {
        //过滤条件
        //获取所有部门
        Integer pageSize = Integer.parseInt(limit);
        Integer pageIndex = Integer.parseInt(page);
        List<Manager> list = managerService.getAllManager();
        //获取分页数据
        pageIndex = (pageIndex - 1) * pageSize;

//        //获取分页评价项
        List<Manager> list1 = managerService.getAllManagerByPage(pageIndex, pageSize);
//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/delManagerById",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delManagerById(String managerIds) {

        String[] split = managerIds.split(",");
        boolean isdel = false;
        Integer id = null;
        List<Manager> list = new ArrayList<>();
        for (String mid : split) {
            id = Integer.parseInt(mid);
            list = managerService.getManagerById(id);
            isdel = managerService.delManagerById(Integer.parseInt(mid));
        }
        if (isdel) {
            for (Manager m : list) {
                Integer userId = m.getUserId();
                boolean delUser = userService.deleteById(userId);
            }
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/getManagers", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getManagers(String managerName, String departmentName) {
        //过滤条件
        //获取所有部门
        List<Manager> list = managerService.getManagers(managerName, departmentName);
//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }
}
