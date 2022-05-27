use developer;

-- ----------------------------
-- Table structure for t_ds_user
-- ----------------------------
DROP TABLE IF EXISTS `t_ds_user`;
CREATE TABLE `t_ds_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `user_name` varchar(64) DEFAULT NULL COMMENT 'user name',
  `user_password` varchar(64) DEFAULT NULL COMMENT 'user password',
  `user_type` int(11) DEFAULT NULL COMMENT 'user type, 0:administrator，1:ordinary user',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `phone` varchar(11) DEFAULT NULL COMMENT 'phone',
  `tenant_id` int(11) DEFAULT NULL COMMENT 'tenant id',
  `create_time` datetime DEFAULT NULL COMMENT 'create time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  `queue` varchar(64) DEFAULT NULL COMMENT 'queue',
  `state` int(11) DEFAULT '1' COMMENT 'state 0:disable 1:enable',
  `time_zone` varchar(32) DEFAULT NULL COMMENT 'time zone',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;


-- ----------------------------
-- Records of t_ds_user
-- ----------------------------
INSERT INTO `t_ds_user`
VALUES ('1', 'james', 'b4cc344d25a2efe540adbf2678e2304c', '1', 'james@qq.com', '', '1', '2022-05-20 10:11:12', '2022-05-20 10:11:12', null, 1, null);
