package com.ship.server.controller;


import com.ship.server.mail_service.ThirdPartyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
