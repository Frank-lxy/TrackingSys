package com.jxd.controller;


import com.jxd.model.User;
import com.jxd.model.UserInfo;
import com.jxd.service.IManagerService;
import com.jxd.service.ITeacherService;
import com.jxd.service.IUserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IManagerService managerService;
    @Autowired
    ITeacherService teacherService;

    @RequestMapping("/userList")
    public String studentList() {
        return "userList";
    }

    @RequestMapping("/addUser")
    public String addUser() {
        return "addUser";
    }

    /**
     * 查询全部的用户信息
     * @param limit
     * @param page
     * @return
     */
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

    /**
     * 查询
     * @param userName
     * @param Character
     * @return
     */
    @RequestMapping(value = "/getUsers", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSON getUsers(String userName, String Character) {
        //过滤条件
        //获取所有部门
        List<User> list = new ArrayList<>();
        List<UserInfo> list1=new ArrayList<>();
        if (Character.equals("教师")) {
            Integer role = 2;
            list = userService.getUsers(userName, role);
            for (User u:list){
                UserInfo userInfo=new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "教师");
                list1.add(userInfo);
            }
        } else if (Character.equals("经理")) {
            Integer role = 3;
            list = userService.getUsers(userName, role);
            for (User u:list){
                UserInfo userInfo=new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "经理");
                list1.add(userInfo);
            }
        } else if (Character == "") {
            Integer role = null;
            list = userService.getUsers(userName, role);
            for (User u:list){
                if (u.getRole()==1){
                    UserInfo userInfo=new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "管理员");
                    list1.add(userInfo);
                }else if (u.getRole()==2){
                    UserInfo userInfo=new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "教师");
                    list1.add(userInfo);
                }else if (u.getRole()==3){
                    UserInfo userInfo=new UserInfo(u.getUserId(), u.getUserName(), u.getPassword(), "经理");
                    list1.add(userInfo);
                }
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", list1.size());//一共有多少条数据
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
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

    /**
     * 新增用户
     * @param userName
     * @param password
     * @param Character
     * @return
     */
    @RequestMapping("/addNewUser")
    @ResponseBody
    public boolean addNewUser(String userName, String password, String Character) {
        boolean isAdd = false;
        if (Character.equals("教师")) {
            Integer role = 2;
            isAdd = userService.addUser(userName, password, role);
            if (isAdd) {
                List<User> list = userService.getMaxUserId();
                for (User u : list) {
                    //在教师表中新增一条数据
                    boolean addTeacher = teacherService.addATeacher(userName,u.getUserId());
                }
                return true;
            } else {
                return false;
            }
        } else if (Character.equals("经理")) {
            Integer role = 3;
            isAdd = userService.addUser(userName, password, role);
            if (isAdd) {
                List<User> list = userService.getMaxUserId();
                for (User u : list) {

                        boolean addManager = managerService.addAManager(userName,u.getUserId());

                }
                return true;
            } else {
                return false;
            }
        }else {
            return false;
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
