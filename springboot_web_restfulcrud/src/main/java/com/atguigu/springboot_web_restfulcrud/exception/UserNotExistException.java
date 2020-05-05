package com.atguigu.springboot_web_restfulcrud.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
