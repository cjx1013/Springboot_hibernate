package com.atguigu.springboot_web_restfulcrud.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用启动");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed...当前web项目销毁");
    }
}
