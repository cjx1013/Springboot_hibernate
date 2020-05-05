package com.atguigu.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootLoggingApplicationTests {
    @Test
    void contextLoads() {
        Logger logger = LoggerFactory.getLogger(getClass());
        //日志的级别；
        // 由低到高 trace<debug<info<warn<error
        // 可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("trace日志");
        logger.debug("debug日志……");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root 级别
        logger.info("info日志……");
        logger.warn("warn日志……");
        logger.error("error日志……");
    }

}
