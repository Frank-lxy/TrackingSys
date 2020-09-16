package com.jxd.controller;


import com.jxd.model.User;
import com.jxd.model.UserInfo;
import com.jxd.service.IUserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/userList")
    public String studentList() {
        return "userList";
    }

    @RequestMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    @RequestMapping(value = "/getAllUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getAllUser(String limit, String page) {
        //过滤条件
        //获取所有部门
        Integer pageSize = Integer.parseInt(limit);
        Integer pageIndex = Integer.parseInt(page);
        List<User> list = userService.getAllUser();
        List<UserInfo> list2 = new ArrayList<>();
        List<UserInfo> list3 = new ArrayList<>();
        for (User u : list) {
            if (u.getRole() == 1) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "管理员");
                list3.add(userInfo);
            } else if (u.getRole() == 2) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "教师");
                list3.add(userInfo);
            } else if (u.getRole() == 3) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "经理");
                list3.add(userInfo);
            }
        }
        //获取分页数据
        pageIndex = (pageIndex - 1) * pageSize;
//        //获取分页评价项
        List<User> list1 = userService.getAllUserByPage(pageIndex, pageSize);
        for (User u : list1) {
            if (u.getRole() == 1) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "管理员");
                list2.add(userInfo);
            } else if (u.getRole() == 2) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "教师");
                list2.add(userInfo);
            } else if (u.getRole() == 3) {
                UserInfo userInfo = new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "经理");
                list2.add(userInfo);
            }
        }
//        //将数组转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(list2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list3.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/getUsers", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getUsers(String userName, String Character) {
        //过滤条件
        //获取所有部门
        List<User> list = new ArrayList<>();
        if (Character.contains("教") || Character.contains("师")) {
            Integer role = 2;
            list = userService.getUsers(userName, role);
        } else if (Character.contains("经") || Character.contains("理")) {
            Integer role = 3;
            list = userService.getUsers(userName, role);
        } else if (Character == "") {
            Integer role = null;
            list = userService.getUsers(userName, role);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/inituser", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String inituser(@RequestParam String userId) {
        boolean isUpdate = false;
        isUpdate = userService.updateUserPwd(Integer.parseInt(userId), "123456");
        if (isUpdate) {
            return "重置成功";
        } else {
            return "重置失败";
        }
    }

    @RequestMapping("/addNewUser")
    @ResponseBody
    public String addNewUser(String userName, String password, String Character) {
        boolean isAdd = false;
        if (Character.equals("教师")) {
            Integer role = 2;
            isAdd = userService.addUser(userName, password, role);
            if (isAdd) {
                return "新增成功";
            } else {
                return "新增失败";
            }
        } else if (Character.equals("经理")) {
            Integer role = 3;
            isAdd = userService.addUser(userName, password, role);
            if (isAdd) {
                return "新增成功";
            } else {
                return "新增失败";
            }
        } else {
            return "新增失败";
        }
    }

    @RequestMapping(value = "/updateAdmin", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String updateAdmin(String userId, String password) {
        boolean isUpdate = false;
        isUpdate = userService.updateAdmin(Integer.parseInt(userId), password);
        if (isUpdate) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }



    @RequestMapping("/editUser")
    @ModelAttribute
    public String editUser(Model model, @RequestParam("userId") String userId) {
        List<User> list = userService.getUserById(Integer.parseInt(userId));
        String userName = null;
        String password = null;
        String Character = null;
        for (User u : list) {
            userName = u.getUserName();
            password = u.getPassword();
            if (u.getRole() == 2) {
                Character = "教师";
            } else if (u.getRole() == 3) {
                Character = "经理";
            }
        }
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);
        model.addAttribute("Character", Character);
        return "editUser";


    }

    @RequestMapping("/editAdmin")
    @ModelAttribute
    public String editAdmin(Model model, @RequestParam("userId") String userId) {
        List<User> list = userService.getUserById(Integer.parseInt(userId));
        String password = null;
        for (User u : list) {
            password = u.getPassword();
        }
        model.addAttribute("userId", userId);
        model.addAttribute("password", password);
        return "editAdmin";
    }

    @RequestMapping("/delUserById")
    @ResponseBody
    public String delUserById(String userIds) {

        String[] split = userIds.split(",");
        boolean isdel = false;
        for (String uid : split) {
            isdel = userService.deleteById(Integer.parseInt(uid));
            System.out.println(isdel);
        }
        System.out.println(isdel);
        if (isdel) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
