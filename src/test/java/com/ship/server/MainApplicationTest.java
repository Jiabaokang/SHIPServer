package com.ship.server;

import com.ship.server.dao.IPmsUserDao;
import com.ship.server.entity.salto.PmsUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MainApplicationTest {

    @Autowired
    //@Resource
    private IPmsUserDao pmsUserDao;

    @Test
    public void addPmsUserTest() {
        int insert = pmsUserDao.insert(new PmsUser("+8613480773590", "102"));
        System.out.println("添加成功："+insert);
    }

    @Test
    public void getAllPmsUserTest() {
        List<PmsUser> pmsUsers = pmsUserDao.selectList(null);
        for (PmsUser pmsUser : pmsUsers) {
            System.out.println(pmsUser);
        }
    }
}
