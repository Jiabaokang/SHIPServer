/*
SQLyog Ultimate v8.32
MySQL - 5.5.62 : Database - shiro
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `t_clazz` */

DROP TABLE IF EXISTS `t_clazz`;

CREATE TABLE `t_clazz` (
                           `cla_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
                           `cla_name` varchar(32) NOT NULL COMMENT '班级名',
                           PRIMARY KEY (`cla_id`),
                           UNIQUE KEY `cla_name` (`cla_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_clazz` */

insert  into `t_clazz`(`cla_id`,`cla_name`) values (1,'Java一班'),(2,'UI一班');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
                                `pm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '许可ID',
                                `pm_label` varchar(32) DEFAULT NULL COMMENT '许可标签',
                                PRIMARY KEY (`pm_id`),
                                UNIQUE KEY `pm_label` (`pm_label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='许可表';

/*Data for the table `t_permission` */

insert  into `t_permission`(`pm_id`,`pm_label`) values (2,'order:add'),(3,'order:del'),(1,'order:list');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
                          `ro_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                          `ro_label` varchar(32) DEFAULT NULL COMMENT '角色标签',
                          PRIMARY KEY (`ro_id`),
                          UNIQUE KEY `ro_label` (`ro_label`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_role` */

insert  into `t_role`(`ro_id`,`ro_label`) values (1,'admin'),(2,'user');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
                                     `rp_ro_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联角色ID',
                                     `rp_pm_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联许可ID',
                                     PRIMARY KEY (`rp_ro_id`,`rp_pm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色许可表';

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`rp_ro_id`,`rp_pm_id`) values (1,1),(2,2),(2,3);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
                          `usr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                          `usr_account` varchar(32) DEFAULT NULL COMMENT '用户账号',
                          `usr_password` varchar(32) DEFAULT NULL COMMENT '用户密码',
                          `usr_money` double DEFAULT NULL COMMENT '用户余额',
                          `usr_cla_id` int(11) DEFAULT NULL COMMENT '关联班级id',
                          PRIMARY KEY (`usr_id`),
                          UNIQUE KEY `usr_account` (`usr_account`),
                          KEY `FK_t_user` (`usr_cla_id`),
                          CONSTRAINT `FK_t_user` FOREIGN KEY (`usr_cla_id`) REFERENCES `t_clazz` (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `t_user` */

insert  into `t_user`(`usr_id`,`usr_account`,`usr_password`,`usr_money`,`usr_cla_id`) values (1,'chenxueli','123',800,1),(2,'zs','123',2200,1),(3,'ls','123',3000,2),(4,'ww','123',4000,2),(5,'zl','666',5000,2);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
                               `ur_usr_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联用户ID',
                               `ur_ro_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联角色ID',
                               PRIMARY KEY (`ur_usr_id`,`ur_ro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`ur_usr_id`,`ur_ro_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
