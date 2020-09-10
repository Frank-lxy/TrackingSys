package com.jxd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Li xinYu
 * @version 1.0
 * @Descrition :
 * @date : 2020-09-09 19:05
 **/
@Controller
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "index";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "index";
    }


    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "index";
    }
}


