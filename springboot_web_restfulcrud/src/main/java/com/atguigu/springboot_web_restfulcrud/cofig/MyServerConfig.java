package com.atguigu.springboot_web_restfulcrud.cofig;

import com.atguigu.springboot_web_restfulcrud.filter.MyFilter;
import com.atguigu.springboot_web_restfulcrud.listener.MyListener;
import com.atguigu.springboot_web_restfulcrud.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import java.util.Arrays;

public class MyServerConfig {
    //注册三大组件
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterRegistrationBean;
    }

    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyListener());
        return servletListenerRegistrationBean;
    }

//    /配置嵌入式的Servlet容器
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //定制嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8083);
//            }
//        };
//    }
}
