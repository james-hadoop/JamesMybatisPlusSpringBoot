CREATE TABLE `t_ds_user` (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
                             `user_name` varchar(64) DEFAULT NULL COMMENT 'user name',
                             `user_password` varchar(64) DEFAULT NULL COMMENT 'user password',
                             `user_type` tinyint(4) DEFAULT NULL COMMENT 'user type, 0:administrator，1:ordinary user',
                             `email` varchar(64) DEFAULT NULL COMMENT 'email',
                             `phone` varchar(11) DEFAULT NULL COMMENT 'phone',
                             `tenant_id` int(11) DEFAULT NULL COMMENT 'tenant id',
                             `create_time` datetime DEFAULT NULL COMMENT 'create time',
                             `update_time` datetime DEFAULT NULL COMMENT 'update time',
                             `queue` varchar(64) DEFAULT NULL COMMENT 'queue',
                             `state` tinyint(4) DEFAULT 1 COMMENT 'state 0:disable 1:enable',
                             `time_zone` varchar(32) DEFAULT NULL COMMENT 'time zone',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `t_ds_user`
VALUES ('1', 'admin', '7ad2410b2f4c074479a8937a28a22b8f', '0', 'xxx@qq.com', '', '0', '2018-03-27 15:48:50', '2018-10-24 17:40:22', null, 1, null);

select * from t_ds_user;


DROP TABLE IF EXISTS `t_ds_session`;
CREATE TABLE `t_ds_session` (
                                `id` varchar(64) NOT NULL COMMENT 'key',
                                `user_id` int(11) DEFAULT NULL COMMENT 'user id',
                                `ip` varchar(45) DEFAULT NULL COMMENT 'ip',
                                `last_login_time` datetime DEFAULT NULL COMMENT 'last login time',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ds_session
-- ----------------------------


