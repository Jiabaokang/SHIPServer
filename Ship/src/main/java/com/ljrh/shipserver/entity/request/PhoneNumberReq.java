package com.ljrh.shipserver.entity.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PhoneNumberReq {
    @JacksonXmlProperty(localName = "PhoneNumber")
    private String phoneNumber;
}
