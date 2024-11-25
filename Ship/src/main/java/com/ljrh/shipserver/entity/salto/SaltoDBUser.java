package com.ljrh.shipserver.entity.salto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("salto_user")
public class SaltoDBUser{

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JacksonXmlProperty(localName = "ExtUserID")
    private String extUserId;

    @JacksonXmlProperty(localName = "ExtPartitionID")
    private String extPartitionId;

    @JacksonXmlProperty(localName = "Title")
    private String title;

    @JacksonXmlProperty(localName = "FirstName")
    private String firstName;

    @JacksonXmlProperty(localName = "LastName")
    private String lastName;

    @JacksonXmlProperty(localName = "PhoneNumber")
    private String phoneNumber;

}
