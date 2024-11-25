package com.ljrh.pmssocket.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("third_pms_user")
public class ThirdPmsUser {

    @TableId(type = IdType.AUTO)
    private Integer addId;
    private String phoneNumber;
    private String type;
    private String encoderCode;
    private String encoderType;
    private String roomName;
    private String roomName2;
    private String roomName3;
    private String roomName4;
    private String authorisationsGranted ;
    private String authorisationsDenied  ;
    private String startingTime;
    private String endingTime;
    private String reservedUse;
    private String infoTrack1;
    private String infoTrack2;
    private String infoTrack3;
    private boolean returnCardSerialNumber;
    private String authorisationCode;

}
