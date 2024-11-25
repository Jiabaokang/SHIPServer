package com.ljrh.shipserver.entity.pms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 指令内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxUser {
    private String phoneNumber;
    private String roomName;
}
