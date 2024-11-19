package com.ship.server.entity.salto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("SaltoUser")
public class SaltoDBUser{

    @JacksonXmlProperty(localName = "ExtUserID")
    private String extUserID;

    @JacksonXmlProperty(localName = "ExtPartitionID")
    private String extPartitionID;

//    @JacksonXmlProperty(localName = "Title")
//    private String title;

    @JacksonXmlProperty(localName = "FirstName")
    private String firstName;

    @JacksonXmlProperty(localName = "LastName")
    private String lastName;

    @JacksonXmlProperty(localName = "PhoneNumber")
    private String phoneNumber;

}
