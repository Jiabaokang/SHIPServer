package com.ship.server.controller;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ship.server.bean.MailRequest;
import com.ship.server.bean.MailRequestXml;
import com.ship.server.mail_service.MailService;
import com.ship.server.utils.JacksonXmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供给外部调用接口
 */
@RestController
@Slf4j
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * JavaBean 转为 XML格式
     */
    private final XmlMapper xmlMapper = new XmlMapper();


    /**
     * 发送邮件，接受XML 数据响应为 XML 数据
     * @param request   邮件请求体
     * @return          邮件响应体
     */
    @PostMapping(value = "/send-mail-send-xml",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendMail(@RequestBody MailRequestXml request) {
        try {
            mailService.sendSimpleMail(request.getTo(), request.getSubject(), request.getContent());
            request.setContent("邮件发送到" + request.getTo() + "-> 成功,发送 XML数据响应为 XML 数据");

            //return ResponseEntity.status(HttpStatus.OK).body(requestXml);

            //将JavaBean转为XML格式
            String res = JacksonXmlConverter.beanToXml(request);
            //String res = xmlMapper.writeValueAsString(request);
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            log.error("邮件发送失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("邮件发送失败");
        }
    }

    /**
     * 发送邮件，发送JSON 数据响应为XML 数据
     * @param request   邮件请求体
     * @return          邮件响应体
     */
    @PostMapping(value = "/send-mail-send-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendMailSendJson(@RequestBody MailRequest request) {
        try {
            //执行发送邮件
            mailService.sendSimpleMail(request.getTo(), request.getSubject(), request.getContent());
            //组装数据
            MailRequestXml temp = new MailRequestXml();
            temp.setTo(request.getTo());
            temp.setSubject(request.getSubject());
            temp.setContent("邮件发送到" + request.getTo() + "-> 成功,发送 JSON 数据响应为 XML 数据");
            log.info("邮件发送成功:{}", temp);
            //将JavaBean转为XML格式
            String res = xmlMapper.writeValueAsString(temp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            log.error("邮件发送失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("邮件发送失败");
        }
    }

}
