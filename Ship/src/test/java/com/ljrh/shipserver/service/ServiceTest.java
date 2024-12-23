package com.ljrh.shipserver.service;
import com.ljrh.shipserver.entity.pms.WxUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {


    @Resource
    MailService mailService;

    @Resource
    PMSService pmsService;

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail("jiabaokangsy@126.com","这是第二份邮件","这是我用Java发送的第二份邮件");
    }

    @Test
    public void saveContentForUserTest() {
        pmsService.onlineDoorOpen(new WxUser("+8613480773570","D102"));
    }
}
