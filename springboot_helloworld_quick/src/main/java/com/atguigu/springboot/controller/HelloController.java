package com.atguigu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// @ResponseBody放在类上表示这个类的所有方法返回的数据直接写给浏览器（如果是对象返回json）

/*@ResponseBody
@Controller*/
//@RestController相当于@ResponseBody和@Controller
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "helloworld,quick";
    }
}
