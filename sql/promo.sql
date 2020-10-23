/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `promo`
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//秒杀活动商品名称',
  `start_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '//活动开始时间',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '//秒杀活动商品id',
  `promo_item_price` double NOT NULL DEFAULT '0' COMMENT '//秒杀活动商品价格',
  `end_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '//秒杀活动结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES ('1', '小米抢购', '2020-10-23 10:46:55', '7', '799', '2020-10-23 15:30:42');
