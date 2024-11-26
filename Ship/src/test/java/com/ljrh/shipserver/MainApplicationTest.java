package com.ljrh.shipserver;


import com.google.gson.Gson;
import com.ljrh.shipserver.dao.IPmsUserDao;
import com.ljrh.shipserver.entity.salto.PmsUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MainApplicationTest {

    @Autowired
    private IPmsUserDao pmsUserDao;

    @Test
    public void getAllPmsUserTest() {
        List<PmsUser> pmsUsers = pmsUserDao.selectList(null);
        for (PmsUser pmsUser : pmsUsers) {
            System.out.println(pmsUser);
        }

        long count = pmsUserDao.selectCount(null);
        System.out.println("总条数:"+count);
    }



}
