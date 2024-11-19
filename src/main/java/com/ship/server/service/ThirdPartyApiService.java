package com.ship.server.service;


import com.ship.server.entity.salto.RequestResponse;
import com.ship.server.entity.salto.SaltoDBUser;
import com.ship.server.utils.JacksonXmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ThirdPartyApiService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public List<SaltoDBUser> saltoDBUserList;

    @Value("${salto_server_url}")
    private String saltoUrl;

    public String callThirdPartyApi(String apiKey, String apiUrl) {
        try {
            // 调用第三方API
            String response = restTemplate.getForObject(apiUrl, String.class);

            // TODO 在这类可以包装一层  统一返回格式
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling third party API: " + e.getMessage();
        }
    }

    /**
     * 调用在线开门
     */
    public String callOnlineDoorOpen(String doorID) {

        String requestBody = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<RequestCall>\n" +
                "    <RequestName>OnlineDoor.Open</RequestName>\n" +
                "    <Params>\n" +
                "        <DoorNameList>\n" +
                "            <DoorID>%s</DoorID>\n" +
                "        </DoorNameList>\n" +
                "    </Params>\n" +
                "</RequestCall>",doorID);

        // 设置请求头
        HttpHeaders headers = getHttpHeaders();
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            // 发送POST请求
            String response = restTemplate.postForObject(saltoUrl, requestEntity, String.class);
            log.info("离线开门成功:{}", response);
            return response;
        } catch (Exception e) {
            log.error("离线开门失败:{}", e.getMessage());
            return "离线开门失败: " + e.getMessage();
        }
    }

    @NotNull
    public HttpHeaders getHttpHeaders() {
        MediaType mediaType = new MediaType("application", "vnd.saltosystems.ship.v1+xml");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return headers;
    }


    /**
     * 60秒执行一次
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 20000)
    public void getUserList() throws IOException {
        getNewUserList();
    }

    /**
     * 取新的用户列表
     * @throws IOException
     */
    private void getNewUserList() throws IOException {
        String requestData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ReguestCall>\n" +
                "    <RequestName> SaltoDBUserList.Read </RequestName>\n" +
                "    <Params>\n" +
                "        <MaxCount>10000</MaxCount>\n" +
                "    </Params>\n" +
                "</ReguestCall>";

        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestData, httpHeaders);

        String response = restTemplate.postForObject(saltoUrl, requestEntity, String.class);
        log.info("获取用户列表:{}", response);
        // 解析响应数据
        RequestResponse data = JacksonXmlConverter.xmlToBean(response, RequestResponse.class);
        List<SaltoDBUser> newUserList= data.getParams().getSaltoDBUserList().getSaltoDBUser();
        //第一次可能是空的
        if (saltoDBUserList.isEmpty()){
            saltoDBUserList.addAll(newUserList);
        }else{
            int oldSize = saltoDBUserList.size();
            int newSize = newUserList.size();
            if (oldSize != newSize){
                // 清空旧列表
                saltoDBUserList.clear();
                // 添加新列表
                saltoDBUserList.addAll(newUserList);
            }
        }

        int userCount = saltoDBUserList.size();

        log.info("获取用户列表:{}", userCount);
        log.info("获取用户列表:{}", saltoDBUserList);
    }


    /**
     * 根据电话号码获取用户信息
     * @param phoneNumber
     * @return
     */
    public SaltoDBUser getUserByPhoneNumber(String phoneNumber) {

        log.info("获取电话号:"+phoneNumber);
        log.info("用户列表:{}",saltoDBUserList);
        SaltoDBUser saltoDBUser = null;

        for (SaltoDBUser dbUser : saltoDBUserList) {
            String phone = dbUser.getPhoneNumber();
            if (phoneNumber.equals(phone)) {
                saltoDBUser = dbUser;
                break;
            }
        }
        return saltoDBUser;
    };



}
