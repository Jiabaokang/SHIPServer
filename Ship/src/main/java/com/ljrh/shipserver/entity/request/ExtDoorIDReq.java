package com.ljrh.shipserver.entity.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExtDoorIDReq {

    @JacksonXmlProperty(localName = "DoorName")
    private String doorName;
}
