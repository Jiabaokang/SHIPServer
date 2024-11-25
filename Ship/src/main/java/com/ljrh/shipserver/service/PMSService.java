package com.ljrh.shipserver.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljrh.shipserver.dao.IPmsUserDao;
import com.ljrh.shipserver.dao.ISaltoUserDao;
import com.ljrh.shipserver.entity.pms.WxUser;
import com.ljrh.shipserver.entity.salto.PmsUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Slf4j
public class PMSService {

    @Autowired
    private IPmsUserDao pmsUserDao;


    /**
     * 保存用户信息
     *
     * @param user 指令内容
     */
    public String onlineDoorOpen(WxUser user) {
        String phoneName = user.getPhoneNumber();

        QueryWrapper<PmsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", phoneName);

        PmsUser pmsUser = pmsUserDao.selectOne(queryWrapper);
        return pmsUser.toString();
    }



}
