package com.ship.server.controller;


import com.ship.server.entity.request.ExtDoorIDReq;
import com.ship.server.entity.request.PhoneNumberReq;
import com.ship.server.entity.salto.SaltoDBUser;
import com.ship.server.service.ThirdPartyApiService;
import com.ship.server.utils.JacksonXmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class ThirdPartyApiController {

    @Autowired()
    private ThirdPartyApiService thirdPartyApiService;

    @GetMapping("/api/call-third-party")
    public ResponseEntity<?> callThirdPartyApi() {
        String apiUrl = "https://demo-api.apipost.cn/api/demo/news_details?id=20&status=1";
        String response = thirdPartyApiService.callThirdPartyApi("",apiUrl);
        try{
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping(
            value = "/api/onlineDoorOpen",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?>callOnlineDoorOpen(@RequestBody String xmlBody){
        try {
            log.info("远程开门请求参数：{}",xmlBody);
            String doorID = JacksonXmlConverter.xmlToBean(xmlBody, ExtDoorIDReq.class).getDoorName();
            String resData = thirdPartyApiService.callOnlineDoorOpen(doorID);
            //将JavaBean转为XML格式
            String res = JacksonXmlConverter.beanToXml(resData);
            log.info("远程开门响应参数：{}",resData);
            return ResponseEntity.ok(resData);

        } catch (Exception e) {
            log.error("远程开门失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(
            value = "/api/getUserByPhone",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?>getUserByPhone(@RequestBody String xmlBody){
        try {
            log.info("获取用户详情参数：{}",xmlBody);
            String phoneNumber = JacksonXmlConverter.xmlToBean(xmlBody, PhoneNumberReq.class).getPhoneNumber();
            SaltoDBUser resData = thirdPartyApiService.getUserByPhoneNumber(phoneNumber);
            //将JavaBean转为XML格式
            String res = JacksonXmlConverter.beanToXml(resData);
            log.info("用户详情响应参数：{}",res);
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            log.error("远程开门失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
