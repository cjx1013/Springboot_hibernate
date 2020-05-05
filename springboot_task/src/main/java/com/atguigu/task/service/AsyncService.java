package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    //告诉spring这是一个异步任务
    @Async
    public void hello(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中……");
    }
}
