package com.atguigu.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskApplicationTests {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("通知-今晚开会");
        mailMessage.setText("今晚9:30开会");
        mailMessage.setFrom("1652565010@qq.com");
        mailMessage.setTo("cjx1013@163.com");
        javaMailSender.send(mailMessage);
    }
    @Test
    public void test02() throws  Exception{
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

        helper.setTo("17512080612@163.com");
        helper.setFrom("534096094@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));

        javaMailSender.send(mimeMessage);

    }

}
