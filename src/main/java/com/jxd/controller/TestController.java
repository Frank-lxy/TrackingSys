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
    @RequestMapping("/hel2")
    public String hel(){
        return "index";
    }

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "index";
    }
}


