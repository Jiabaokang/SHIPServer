package com.ship.server.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * 使用 Jackson 将 JavaBean 和 XML 互相转换的工具类
 * JavaBean的定义-示例如下：
 * 指定 XML 根节点的名称。
 * @JacksonXmlRootElement(localName = "Person")
 * public class Person {
 *      定义 XML 节点名称与 JavaBean 属性的映射关系。
 *     @JacksonXmlProperty(localName = "name")
 *     private String name;
 *
 *     @JacksonXmlProperty(localName = "age")
 *     private int age;
 *     public Person() {} // 必须包含无参构造方法
 *   }
 */
public class JacksonXmlConverter {

    /**
     * Java对象转XML字符串
     */
    public static String beanToXml(Object bean) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(bean);
    }

    /**
     * XML字符串转Java对象
     */
    public static <T> T xmlToBean(String xmlStr, Class<T> clazz) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper.readValue(xmlStr, clazz);
    }
}
