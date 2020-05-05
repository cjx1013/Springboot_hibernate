package com.atguigu.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit //开启基于注解的rabbitmq模式
@SpringBootApplication
public class SpringbootAmqbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqbApplication.class, args);
    }

}
