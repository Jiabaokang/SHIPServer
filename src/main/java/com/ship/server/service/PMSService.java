package com.ship.server.service;


import com.ship.server.entity.pms.ComContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Slf4j
public class PMSService {

    /**
     * 保存用户信息
     * @param comContent 指令内容
     */
    public String saveContentForUser(ComContent comContent) {
        String comContentStr = comContent.getComContent();
        //STX|CNM|+8613480773570|101|ETX LRC
        if (comContentStr != null && comContentStr.contains("|")) {
            String[] comSet = comContentStr.split("\\|");
            if (comSet.length > 4) {
                String phone = comSet[2];
                String room = comSet[3];
                log.info("用户手机号-->{}",phone);
                log.info("用户房间号-->{}",room);
            }
        }
        log.info("保存用户信息");

        String resultMsg = sendMessageToSaltoPMS(comContentStr);

        return resultMsg;
    }

    public String sendMessageToSaltoPMS(String comContent) {


        log.info("发送消息给salto pms");

        return "STX| CNM|+34619123 456| ETX LRC";

    }

}
