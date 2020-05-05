package com.atguigu.springboot_web_restfulcrud.controller;

import com.atguigu.springboot_web_restfulcrud.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello World";
    }


    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h4>你好</h4>");
        map.put("users", Arrays.asList("lisi","zhangsan","wangwu"));
        return "success";
    }
}
