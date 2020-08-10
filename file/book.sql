/*
 Navicat Premium Data Transfer

 Source Server         : mage
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.56.101:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 10/08/2020 10:25:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `sales` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `img_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (1, 'java从入门到放弃', '国哥', 80.00, 10000, 8, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (2, '数据结构与算法', '严敏君', 78.50, 8, 11, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (3, '怎样拐跑别人的媳妇', '龙伍', 68.00, 100000, 51, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (4, '木虚肉盖饭', '小胖', 16.00, 1001, 49, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (5, 'C++编程思想', '刚哥', 45.50, 14, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (6, '蛋炒饭', '周星星', 9.90, 12, 53, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (7, '赌神', '龙伍', 66.50, 125, 535, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (8, 'Java编程思想', '阳哥', 99.50, 47, 36, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (9, 'JavaScript从入门到精通', '婷姐', 9.90, 85, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (10, 'cocos2d-x游戏编程入门', '国哥', 49.00, 52, 62, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (11, 'C语言程序设计', '谭浩强', 28.00, 52, 74, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (12, 'Lua语言程序设计', '雷丰阳', 51.50, 48, 82, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (13, '西游记', '罗贯中', 12.00, 19, 9999, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (14, '水浒传', '华仔', 33.05, 22, 88, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (15, '操作系统原理', '刘优', 133.05, 122, 188, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (16, '数据结构 java版', '封大神', 173.15, 21, 81, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (17, 'UNIX高级环境编程', '乐天', 99.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (18, 'javaScript高级编程', '国哥', 69.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (19, '大话设计模式', '国哥', 89.15, 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (20, '人月神话', '刚哥', 88.15, 20, 80, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (21, '时间简史qzp', 'qzp', 10000.00, 1000000, 0, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (22, '修改了', 'qzp', 123.00, 123, 123, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (24, '试试看', 'qzp', 10000.00, 123, 0, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (26, '我来了', 'qzp', 100.00, 1000000, 0, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (27, '我太难了', 'qzp', 10000.00, 1000000, 123, 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `total_price` decimal(11, 2) NULL DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'admin@atguigu.com');
INSERT INTO `t_user` VALUES (2, 'root', 'root', 'qzpwjw@6015');
INSERT INTO `t_user` VALUES (4, 'lihua', '123456', 'lihua@sina.com');
INSERT INTO `t_user` VALUES (7, 'wangjiawen', 'qzp', '12345@qq.com');
INSERT INTO `t_user` VALUES (8, 'adminqzp', 'admin', 'admin@atguigu.com');
INSERT INTO `t_user` VALUES (9, '12345', '12345', 'admin@atguigu.com');
INSERT INTO `t_user` VALUES (10, 'admin123', 'admin', 'admin@atguigu.com');

SET FOREIGN_KEY_CHECKS = 1;
