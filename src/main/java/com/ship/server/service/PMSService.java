package com.ship.server.service;


import com.ship.server.dao.IPmsUserDao;
import com.ship.server.dao.ISaltoUserDao;
import com.ship.server.entity.pms.ComContent;
import com.ship.server.entity.salto.PmsUser;
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


    @Autowired
    ISaltoUserDao saltoUserDao;

    /**
     * 保存用户信息
     *
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
                log.info("用户手机号-->{}", phone);
                log.info("用户房间号-->{}", room);
                try {
                    int insert = pmsUserDao.insert(new PmsUser(phone, room));
                    log.info("保存用户信息成功:{}", insert);
                } catch (Exception e) {
                    return "保存记录失败:" + e.getMessage();
                }
            } else {
                return "指令格式错误";
            }
        }
        String resultMsg = sendMessageToSaltoPMS(comContentStr);
        return resultMsg;
    }


    /**
     * 发送消息给SaltoPms
     *
     * @param comContent 指令内容
     *  这里需要完成的任务:
     *  1. 发送消息给SaltoPms
     *  2. 接收SaltoPms的返回消息
     *  3. 根据手机号码-查询本地用户信息-拿到extUserID
     *  4. 根据extUserID获取用户详细数据
     *  4. extUserID获取用户详细数据-拿到房间号
     *
     */
    public String sendMessageToSaltoPMS(String comContent) {




        log.info("发送消息给salto pms");

        return "STX| CNM|+34619123456| ETX LRC";

    }

}
