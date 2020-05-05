package com.atguigu.springboot_web_restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/user/login")
    public String hello(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }
}
