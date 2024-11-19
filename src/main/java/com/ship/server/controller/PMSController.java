package com.ship.server.controller;


import com.ship.server.entity.pms.ComContent;
import com.ship.server.service.PMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PMSController {

    @Autowired
    private PMSService pmsService;

    @PostMapping("/pmsSendUserInfo")
    public ResponseEntity<?> pmsSendUserInfo(@RequestBody ComContent body){
        log.info("body:{}",body);
       String result =  pmsService.saveContentForUser(body);

       return ResponseEntity.ok(result);
    }
}
