/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : miaosha

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-23 15:26:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `item_stock`
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `STOCK_ITEM` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES ('1', '50', '1');
INSERT INTO `item_stock` VALUES ('2', '50', '2');
INSERT INTO `item_stock` VALUES ('3', '9', '4');
INSERT INTO `item_stock` VALUES ('4', '10', '5');
INSERT INTO `item_stock` VALUES ('5', '1', '6');
INSERT INTO `item_stock` VALUES ('6', '50', '3');
INSERT INTO `item_stock` VALUES ('7', '45', '7');
