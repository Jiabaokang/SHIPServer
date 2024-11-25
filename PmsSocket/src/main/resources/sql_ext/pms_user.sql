/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`Salto` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `Salto`;

/**
  创建表第三方pms 用户表
 */
DROP TABLE IF EXISTS `third_pms_user`;
CREATE TABLE third_pms_user
(
    addId                     INT AUTO_INCREMENT PRIMARY KEY COMMENT '添加ID',
    phone_number              VARCHAR(64) NOT NULL COMMENT '手机号码',
    type                      VARCHAR(64) COMMENT '默认填写:CN',
    encoder_code              VARCHAR(64) COMMENT '编码器编号:1',
    encoder_type              VARCHAR(64) COMMENT '编码器类型默认:E',
    room_name                 VARCHAR(64) NOT NULL COMMENT '房间号-由卡片打开的第一个房间主房间',
    room_name2                VARCHAR(64) COMMENT '房间号-第二个房间主房间',
    room_name3                VARCHAR(64) COMMENT '房间号-第三个房间主房间',
    room_name4                VARCHAR(64) COMMENT '房间号-第4️四个房间主房间',
    authorisations_granted    VARCHAR(64) COMMENT '同意授权的编号',
    authorisations_denied     VARCHAR(64) COMMENT '拒绝授权的编号',
    starting_time             VARCHAR(64) COMMENT '该卡的开始日期和时间',
    ending_time               VARCHAR(64) COMMENT '该卡的结束日期和时间',
    reserved_use              VARCHAR(64) COMMENT '预留字段',
    info_track1               VARCHAR(64) COMMENT '信息写在轨道#1上',
    info_track2               VARCHAR(64) COMMENT '信息写在轨道#2上',
    info_track3               VARCHAR(64) COMMENT '信息写在轨道#3上',
    return_card_serial_number BOOLEAN COMMENT 'PC接口是否要返回板卡的序列号',
    authorisation_code        VARCHAR(64) COMMENT '分配给房间客人的授权代码'
) ENGINE = InnoDb
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
