/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '//用户id',
  `itemId` int(11) NOT NULL COMMENT '//商品id',
  `item_price` double NOT NULL DEFAULT '0' COMMENT '//商品价格',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '//购买数量',
  `order_price` double NOT NULL DEFAULT '0' COMMENT '//订单价格',
  `promo_id` int(11) DEFAULT '0' COMMENT '//非0则为秒杀价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2020102200000100', '16', '7', '800', '1', '800', '0');
INSERT INTO `order_info` VALUES ('2020102300000200', '16', '7', '800', '1', '800', '0');
INSERT INTO `order_info` VALUES ('2020102300000300', '16', '7', '800', '1', '800', '0');
INSERT INTO `order_info` VALUES ('2020102300000400', '16', '7', '799', '1', '799', '1');
