package com.ljrh.shipserver.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailReq {

    private String to;
    private String subject;
    private String content;


}
