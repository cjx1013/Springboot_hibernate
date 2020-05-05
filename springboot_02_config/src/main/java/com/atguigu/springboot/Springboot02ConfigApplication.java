package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ImportResource(value = {"classpath:beans.xml"})//加载指定spring配置文件
@SpringBootApplication
public class Springboot02ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02ConfigApplication.class, args);
    }

}
