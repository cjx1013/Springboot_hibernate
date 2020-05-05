package com.atguigu.springboot.service;

import com.atguigu.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = "atguigu.news")
    public void receive01(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
