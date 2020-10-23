/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT '0' COMMENT '//为1代表男性，2代表女性',
  `age` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `register_mode` varchar(255) DEFAULT NULL COMMENT '//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone_unique_index` (`telephone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('15', 'admin', '1', '20', '15902993597', 'byPhone', null);
INSERT INTO `user_info` VALUES ('16', 'MOM', '2', '50', '13110432089', 'byPhone', null);
INSERT INTO `user_info` VALUES ('18', 'dad', '1', '50', '13402941949', 'byPhone', null);
