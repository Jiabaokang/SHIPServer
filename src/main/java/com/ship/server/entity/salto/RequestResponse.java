package com.ship.server.entity.salto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestResponse {

    @JacksonXmlProperty(localName = "RequestName")
    private String	requestName;

    @JacksonXmlProperty(localName = "Params")
    private Params	params;
}
