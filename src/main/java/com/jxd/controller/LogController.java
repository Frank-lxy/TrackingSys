package com.jxd.controller;

import com.jxd.model.User;
import com.jxd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 登录退出控制
 * @Author Song SaiSai
 * @Date 2020/9/11 17:51
 * @Version 1.0
 */
@Controller
@SessionAttributes({"user"})
public class LogController {
    @Autowired
    IUserService userService;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //移除session
        request.getSession().invalidate();
        return "forward:";
    }

    @RequestMapping(value = "/login",produces = "text/html;charset=utf-8")
    public String login(User user, String remember, Model model, HttpServletResponse response){
        //根据用户名密码查询用户
        user = userService.getUser(user);
        //如果用户不为null，用户名、密码输入正确
        if (user != null){
            //判断是否记住密码
            if ("y".equals(remember)){
                //如果勾选记住密码，将用户名和密码存入cookie中
                Cookie usernameCookie = new Cookie("usernameCookie",user.getUserName());
                usernameCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(usernameCookie);

                Cookie userpasswordCookie = new Cookie("userpasswordCookie",user.getPassword());
                userpasswordCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(userpasswordCookie);
            }
            //将user存入session中
            model.addAttribute("user",user);
            return "main";
        }else {//否则返回记住密码前的登录页面
            model.addAttribute("loginMsg","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping(value = "/",produces = "text/html;charset=utf-8")
    public String getLogin(HttpServletRequest request,Model model){
        String userName = "";
        String password = "";
        //获取cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            //循环查找系统存储的cookie，如果找到，则存入请求中
            for (int i = 0; i < cookies.length;i++){
                if ("usernameCookie".equals(cookies[i].getName())){
                    userName = cookies[i].getValue();
                }else if ("userpasswordCookie".equals(cookies[i].getName())){
                    password = cookies[i].getValue();
                }
            }
            model.addAttribute("userName",userName);
            model.addAttribute("password",password);
        }
        return "login";
    }
}
