package com.ljrh.shipserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljrh.shipserver.dao.IPmsUserDao;
import com.ljrh.shipserver.entity.pms.WxUser;
import com.ljrh.shipserver.entity.salto.PmsUser;
import com.ljrh.shipserver.service.PMSService;
import com.ljrh.shipserver.service.ThirdPartyApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@Slf4j
public class PMSController {

    @Autowired
    private PMSService pmsService;

    @Autowired
    private IPmsUserDao pmsUserDao;

    @Autowired
    private ThirdPartyApiService thirdPartyApiService;


    @PostMapping("/onlineDoorOpen")
    public ResponseEntity<?> pmsSendUserInfo(@RequestBody WxUser wxUser){
        log.info("body:{}",wxUser);

        QueryWrapper<PmsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", wxUser.getPhoneNumber());

        PmsUser pmsUser = pmsUserDao.selectOne(queryWrapper);
        String startTime = pmsUser.getStartingTime();
        String endingTime = pmsUser.getEndingTime();
        String currentTime = String.format(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")), System.currentTimeMillis());

        //TODO 判断当前日期是否在有效期内

        String result = thirdPartyApiService.callOnlineDoorOpen(wxUser.getRoomName());

        //String result =  pmsService.onlineDoorOpen(wxUser);
       return ResponseEntity.ok(result);
    }

}
