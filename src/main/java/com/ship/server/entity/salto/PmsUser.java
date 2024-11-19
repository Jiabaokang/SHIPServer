package com.ship.server.entity.salto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("PmsUser")
public class PmsUser {

    private String phoneNumber;
    private String roomName;
}
