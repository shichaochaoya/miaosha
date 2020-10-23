/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL DEFAULT '',
  `price` double(10,0) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '',
  `sales` int(11) DEFAULT '0',
  `img_url` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '1', '1', '1', '1', '1');
INSERT INTO `item` VALUES ('2', '2', '2', '2', '2', '2');
INSERT INTO `item` VALUES ('3', '3', '3', '3', '50', '3');
INSERT INTO `item` VALUES ('4', '9', '9', '9', '0', '9');
INSERT INTO `item` VALUES ('5', '海信电视', '17999', '海信激光电视', '0', '无');
INSERT INTO `item` VALUES ('6', '守望先锋', '299', '守望先锋sb游戏', '0', 'C:\\fakepath\\1e0e57a4de33e81beef6ef367603872.jpg');
INSERT INTO `item` VALUES ('7', '小米99', '800', '最好用的手机', '0', 'https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2579981163,987947911&fm=26&gp=0.jpg');
