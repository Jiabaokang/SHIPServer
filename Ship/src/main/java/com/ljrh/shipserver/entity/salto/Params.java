package com.ljrh.shipserver.entity.salto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Params {




//    @JacksonXmlElementWrapper(useWrapping = false) // 防止包裹元素
//    @JacksonXmlProperty(localName = "SaltoDBUser")
//    private List<SaltoDBUser> saltoDBUserList;

    @JacksonXmlProperty(localName = "SaltoDBUserList")
    private SaltoDBUserList saltoDBUserList;

    @JacksonXmlProperty(localName = "EOF")
    private int eof;

}
