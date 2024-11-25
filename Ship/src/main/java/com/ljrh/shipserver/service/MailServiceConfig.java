package com.ljrh.shipserver.service;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MailServiceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public List<SaltoDBUser> saltoDBUserList() {
//        return new ArrayList<SaltoDBUser>();
//    }

//    @Bean
//    public XmlMapper getXmlMapper() {
//        return new XmlMapper();
//    }

//    @Bean
//    public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
//        return new MarshallingHttpMessageConverter();
//    }

//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        // 设置 JAXB 上下文路径，这里假设您的 JAXB 绑定类位于 "com.ship.server.jaxb" 包下
//        marshaller.setPackagesToScan("com.ship.server.bean");
//        return marshaller;
//    }
}
