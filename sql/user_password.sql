/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_password`
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) NOT NULL DEFAULT '',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('5', 'rlk/ksz9+dpK2AjDsLUXjQ==', '15');
INSERT INTO `user_password` VALUES ('6', 'rlk/ksz9+dpK2AjDsLUXjQ==', '16');
INSERT INTO `user_password` VALUES ('7', 'rlk/ksz9+dpK2AjDsLUXjQ==', '18');
