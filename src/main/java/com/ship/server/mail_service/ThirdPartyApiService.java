package com.ship.server.mail_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyApiService {

    @Autowired
    public RestTemplate restTemplate;

    public String callThirdPartyApi(String apiKey, String apiUrl) {
        try{
            // 调用第三方API
            String response = restTemplate.getForObject(apiUrl, String.class);

            // TODO 在这类可以包装一层  统一返回格式
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling third party API: " + e.getMessage();
        }
    }


}
