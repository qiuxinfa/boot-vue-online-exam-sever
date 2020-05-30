/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : boot_vue_online_exam

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-05-30 21:09:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `dict_code` varchar(10) NOT NULL COMMENT '字典代码',
  `dict_value` varchar(50) NOT NULL COMMENT '字典值',
  `dict_type_code` varchar(20) NOT NULL COMMENT '字典类型代码',
  `dict_desc` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `dict_order` int(10) DEFAULT NULL COMMENT '字典排序',
  `parent_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典';

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('001', '1', '简单', 'QUESTION_LEVEL', '题目难度等级', '1', null);
INSERT INTO `dict` VALUES ('002', '2', '中等', 'QUESTION_LEVEL', '题目难度等级', '2', null);
INSERT INTO `dict` VALUES ('003', '3', '困难', 'QUESTION_LEVEL', '题目难度等级', '3', null);
INSERT INTO `dict` VALUES ('05186928782f449fb9a5e3c8ccb3e5c1', '001', '教授', 'TEACHER_TYPE', '老师职级', '1', null);
INSERT INTO `dict` VALUES ('2f0801d50ddf11eab421f0761cc5591a', '1', '男', 'SEX', '性别', '1', null);
INSERT INTO `dict` VALUES ('3500f6e20ed111ea98a05c93a27933da', '01', '专业必修', 'COURSE_TYPE', '课程类型', '1', null);
INSERT INTO `dict` VALUES ('382693170ddf11eab421f0761cc5591a', '2', '女', 'SEX', '性别', '2', null);
INSERT INTO `dict` VALUES ('3f2e69ce0ed111ea98a05c93a27933da', '02', '专业选修', 'COURSE_TYPE', '课程类型', '2', null);
INSERT INTO `dict` VALUES ('42c52d13c78d44e09ccc9167e029eea4', '005', '实习老师', 'TEACHER_TYPE', '老师职级', '5', null);
INSERT INTO `dict` VALUES ('4d0623560ed111ea98a05c93a27933da', '03', '公共必修', 'COURSE_TYPE', '课程类型', '3', null);
INSERT INTO `dict` VALUES ('551fabe50ed111ea98a05c93a27933da', '04', '公共选修', 'COURSE_TYPE', '课程类型', '4', null);
INSERT INTO `dict` VALUES ('55c6c5560ddf11eab421f0761cc5591a', '1', '管理员', 'USER_TYPE', '用户类型', '1', null);
INSERT INTO `dict` VALUES ('575b501ead0a4004a6a53297c4d1cc89', '003', '讲师', 'TEACHER_TYPE', '老师职级', '3', null);
INSERT INTO `dict` VALUES ('5e25521a0ddf11eab421f0761cc5591a', '2', '老师', 'USER_TYPE', '用户类型', '2', null);
INSERT INTO `dict` VALUES ('6650d21a0ddf11eab421f0761cc5591a', '3', '学生', 'USER_TYPE', '用户类型', '3', null);
INSERT INTO `dict` VALUES ('89494fa99a8f11ea83105c93a27933da', '1', '优秀', 'GRADE_LEVEL', '成绩等级', '1', '');
INSERT INTO `dict` VALUES ('89c2cb320dd611eab421f0761cc5591a', '1', '模块', 'MENU', '权限菜单类型', '1', null);
INSERT INTO `dict` VALUES ('9c0ab74e0dd611eab421f0761cc5591a', '2', '菜单', 'MENU', '权限菜单类型', '2', null);
INSERT INTO `dict` VALUES ('a3412bbf9a8f11ea83105c93a27933da', '2', '良好', 'GRADE_LEVEL', '成绩等级', '2', '');
INSERT INTO `dict` VALUES ('a4da30530dd611eab421f0761cc5591a', '3', '按钮', 'MENU', '权限菜单类型', '3', null);
INSERT INTO `dict` VALUES ('a9127f74e6214b3f90379f9c2dd3fc93', '002', '副教授', 'TEACHER_TYPE', '老师职级', '2', null);
INSERT INTO `dict` VALUES ('ab9d3cd29a8f11ea83105c93a27933da', '3', '中等', 'GRADE_LEVEL', '成绩等级', '3', '');
INSERT INTO `dict` VALUES ('bbd9ca819a8f11ea83105c93a27933da', '4', '及格', 'GRADE_LEVEL', '成绩等级', '4', '');
INSERT INTO `dict` VALUES ('ce0110249a8f11ea83105c93a27933da', '5', '不及格', 'GRADE_LEVEL', '成绩等级', '5', '');
INSERT INTO `dict` VALUES ('cf9759b9c5bb4224b6de29eca85d94e2', '004', '助教', 'TEACHER_TYPE', '老师职级', '4', null);

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `exam_desc` varchar(200) DEFAULT NULL COMMENT '考试介绍',
  `start_date` date DEFAULT NULL COMMENT '考试开始日期',
  `end_date` date DEFAULT NULL COMMENT '考试截止日期',
  `total_time` int(3) DEFAULT NULL COMMENT '考试时长',
  `major` varchar(32) DEFAULT NULL COMMENT '专业',
  `institute` varchar(32) DEFAULT NULL COMMENT '学院',
  `exam_type` int(2) DEFAULT NULL COMMENT '考试类型',
  `single_score` double(5,2) DEFAULT NULL COMMENT '鍗曢€夐鍒嗘暟',
  `multi_score` double(5,2) DEFAULT NULL COMMENT '澶氶€夐鍒嗘暟',
  `judge_score` double(5,2) DEFAULT NULL COMMENT '鍒ゆ柇棰樺垎鏁?',
  `fill_score` double(5,2) DEFAULT NULL COMMENT '濉┖棰樺垎鏁?',
  `total_score` double(10,2) DEFAULT NULL COMMENT '鎬诲垎',
  `single_ids` varchar(3300) DEFAULT NULL COMMENT '单选题id集合',
  `multi_ids` varchar(3300) DEFAULT NULL COMMENT '多选题id集合',
  `judge_ids` varchar(3300) DEFAULT NULL COMMENT '判断题id集合',
  `fill_ids` varchar(3300) DEFAULT NULL COMMENT '填空题id集合',
  `is_publish` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试安排';

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('001', '测试考试1', '2020-05-26 23:46:47', null, '测试考试1', '2020-05-17', '2020-05-31', '90', null, null, '1', '4.00', '5.00', '2.00', '5.00', '36.00', '120000,120001,120003', '1,2', '110001,110002', '10000,10001', '1');
INSERT INTO `exam` VALUES ('002', '测试考试2', '2020-05-26 23:46:51', null, '测试考试2', '2020-05-17', '2020-06-23', '60', null, null, '2', '3.00', '5.00', '2.00', '5.00', '33.00', '120000,120001,120003', '1,2', '110001,110002', '10000,10001', '1');
INSERT INTO `exam` VALUES ('003', '测试单选题', '2020-05-28 23:46:55', null, '测试单选题', '2020-05-24', '2020-05-30', '10', null, null, '1', '5.00', null, null, null, '15.00', '120000,120001,120003', null, null, null, '1');
INSERT INTO `exam` VALUES ('004', '测试填空题', '2020-05-12 23:46:59', null, '测试填空题', '2020-05-24', '2020-05-30', '10', null, null, '1', null, null, null, '2.00', '4.00', null, null, null, '10000,10001', '1');
INSERT INTO `exam` VALUES ('005', '测试判断题', '2020-05-15 23:47:04', null, '测试判断题', '2020-05-24', '2020-05-30', '5', null, null, '2', null, null, '2.00', null, '4.00', null, null, '110001,110002', null, '1');
INSERT INTO `exam` VALUES ('006', '测试多选题', '2020-05-28 23:47:09', null, '测试多选题', '2020-05-24', '2020-05-30', '5', null, null, '1', null, '5.00', null, null, '10.00', null, '1,2', null, null, '1');
INSERT INTO `exam` VALUES ('4e10473f4cff479881fe6a5be4839eab', '测试随机2', '2020-05-27 16:37:41', null, '测试随机2', null, null, '20', null, null, null, '2.00', '4.00', '1.00', '1.00', '26.00', '', '', '', '', '0');
INSERT INTO `exam` VALUES ('b912489f85994751981afb1c516f5b2e', '测试随机组卷', '2020-05-27 15:04:22', null, '测试随机组卷', null, null, '30', null, null, null, '2.00', '4.00', '2.00', '2.00', '28.00', '', '', '', '', '0');

-- ----------------------------
-- Table structure for `exam_record`
-- ----------------------------
DROP TABLE IF EXISTS `exam_record`;
CREATE TABLE `exam_record` (
  `id` varchar(32) NOT NULL COMMENT '考试记录表的主键',
  `exam_id` varchar(32) DEFAULT NULL COMMENT '考试安排ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '考试参与者的用户id',
  `finish_time` datetime DEFAULT NULL COMMENT '考试完成时间',
  `cost_time` int(3) DEFAULT '0' COMMENT '完成考试所用的时间,单位分钟',
  `final_score` double(5,2) DEFAULT '0.00' COMMENT '闁告瑥鍊风粭宀勬嚀閸愵厾妲搁柣銊ュ閻ゅ嫰姊介崨顓犵箒闁?',
  `result_level` int(2) DEFAULT '0' COMMENT '考试结果的等级',
  `single_answer` varchar(3300) DEFAULT NULL COMMENT '考生单选题答案，题与题之间用*号分隔',
  `multi_answer` varchar(6600) DEFAULT NULL COMMENT '考生多选题答案，题与题之间用*号分隔',
  `judge_answer` varchar(3300) DEFAULT NULL COMMENT '考生判断题答案，题与题之间用*号分隔',
  `fill_answer` varchar(2000) DEFAULT NULL COMMENT '考生填空题答案，题与题之间用*号分隔，同一个题的2个空之间用#号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试记录表';

-- ----------------------------
-- Records of exam_record
-- ----------------------------
INSERT INTO `exam_record` VALUES ('1cb7e636ca59406f9e08124934fcbfb4', '004', '67eb71f1091911eab9205c93a27933da', '2020-05-24 12:24:24', '0', '1.00', '5', '', '', '', 'null,资源子网*?');
INSERT INTO `exam_record` VALUES ('40b3f542231c4f7db79c562ab8f1fb7f', '003', '67eb71f1091911eab9205c93a27933da', '2020-05-30 12:47:16', '0', '10.00', '4', 'C*?*A', '', '', '');
INSERT INTO `exam_record` VALUES ('65f42e15ceaf4ee69211938da519df3d', '005', '67eb71f1091911eab9205c93a27933da', '2020-05-24 12:26:20', '0', '2.00', '5', '', '', '?*F', '');
INSERT INTO `exam_record` VALUES ('8398a72a6ae24d969600a1daf39f7f76', '003', '67eb71f1091911eab9205c93a27933da', '2020-05-24 12:22:15', '0', '5.00', '5', '?*D*?', '', '', '');
INSERT INTO `exam_record` VALUES ('ae0cdf53fab74f608225e099bde39147', '006', '67eb71f1091911eab9205c93a27933da', '2020-05-24 12:28:20', '0', '5.00', '5', '', '?*B,C,D', '', '');
INSERT INTO `exam_record` VALUES ('efd51353a7274d09b4d7fd3b1c474fd0', '003', 'c54880d2de294167a83c97d7f6b94097', '2020-05-30 13:03:04', '0', '10.00', '4', 'C*?*A', '', '', '');
INSERT INTO `exam_record` VALUES ('fd6b466622274a29b0744d97283a8b20', '001', '67eb71f1091911eab9205c93a27933da', '2020-05-24 12:31:18', '1', '22.50', '4', 'C*?*A', '?*B,C,D', '?*F', 'null,资源子网*应用');

-- ----------------------------
-- Table structure for `fill_question`
-- ----------------------------
DROP TABLE IF EXISTS `fill_question`;
CREATE TABLE `fill_question` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `create_id` varchar(32) DEFAULT NULL COMMENT '题目创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '题目创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '题目更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '题目更新时间',
  `question_content` varchar(255) DEFAULT NULL COMMENT '题目内容',
  `question_answer` varchar(255) DEFAULT NULL COMMENT '题目答案',
  `question_explain` varchar(255) DEFAULT NULL COMMENT '题目解析',
  `question_level` int(2) DEFAULT NULL COMMENT '题目难度等级',
  `question_type` int(2) DEFAULT NULL COMMENT '题目分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='填空题';

-- ----------------------------
-- Records of fill_question
-- ----------------------------
INSERT INTO `fill_question` VALUES ('10000', null, '2020-02-16 00:00:00', null, null, '从计算机网络系统组成的角度看，计算机网络可以分为()和()', '通信子网,资源子网', null, '3', '1');
INSERT INTO `fill_question` VALUES ('10001', null, '2020-02-16 00:00:00', null, null, '收发电子邮件，属于ISO/OSI RM中 ()层的功能。', '应用', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10002', null, '2020-02-16 00:00:00', null, null, '在TCP/IP层次模型中与OSI参考模型第四层相对应的主要协议有()和(),其中后者提供无连接的不可靠传输服', 'TCP（传输控制协议） UDP（用户数据报协议） ', null, '2', '1');
INSERT INTO `fill_question` VALUES ('10003', null, '2020-02-16 00:00:00', null, null, '计算机网络中常用的三种有线媒体是 (),()和 ()', '同轴电缆.双绞线 光纤', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10004', null, '2020-02-16 00:00:00', null, null, '国内最早的四大网络包括原邮电部的ChinaNet. 原电子部的ChinaGBN. 教育部的()和中科院的CSTnet', 'CERnet (或中国教育科研网)', null, '4', '1');
INSERT INTO `fill_question` VALUES ('10005', null, '2020-02-16 00:00:00', null, null, '复盖一个国家，地区或几个洲的计算机网络称为()，在同一建筑或复盖几公里内范围的网络称为()，而介于两者之间的是()', ' 广域网       局域网     城域网', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10006', null, '2020-02-16 00:00:00', null, null, 'Outlook等常用电子邮件软件接收邮件使用的协议是(),发送邮件时使用的协议是()', 'POP3    SMTP    ', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10007', null, '2020-02-16 00:00:00', null, null, '通信系统中，称调制前的电信号为()信号，调制后的信号为调制信号', '基带', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10008', null, '2020-02-16 00:00:00', null, null, '按照IPV4标准,IP地址205.3.127.13属于()类地址', 'C', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10009', null, '2020-02-16 00:00:00', null, null, '计算机网络采用()技术，而传统电话网络则采用()技术', '分组交换电路交换', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10010', null, '2020-02-16 00:00:00', null, null, '计算机内传输的信号是()，而公用电话系统的传输系统只能传输()', '数字信号模拟信号', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10011', null, '2020-02-16 00:00:00', null, null, '通信系统中，称调制前的电信号为()，调制后的信号叫()。', '基带信号调制信号', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10012', null, '2020-02-16 00:00:00', null, null, 'IP地址分()和()两个部分', '网络号主机号', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10013', null, '2020-02-16 00:00:00', null, null, ' IP地址协议作网间网中()层协议，提供无连接的数据报传输机制，IP数据报也分为()和()两个部分', '网络报头数据区', null, '2', '1');
INSERT INTO `fill_question` VALUES ('10014', null, '2020-02-16 00:00:00', null, null, '()是一个简单的远程终端协议。', 'TELNET', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10015', null, '2020-02-16 00:00:00', null, null, '在同一个系统内，相邻层之间交换信息的连接点称之为()，而低层模块向高层提供功能性的支持称之为()。', '接口服务', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10016', null, '2020-02-16 00:00:00', null, null, 'Internet广泛使用的电子邮件传送协议是()', 'SMTP', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10017', null, '2020-02-16 00:00:00', null, null, '按交换方式来分类，计算机网络可以分为电路交换网，  报文交换网  和()三种', '分组交换网', null, '3', '1');
INSERT INTO `fill_question` VALUES ('10018', null, '2020-02-16 00:00:00', null, null, 'Intranet分层结构包括网络、(),应用三个层次。', '服务', null, '1', '1');
INSERT INTO `fill_question` VALUES ('10019', null, '2020-02-16 00:00:00', null, null, 'WWW上的每一个网页都有一个独立的地址，这些地址称为  ()', '统一资源定位器/URL ', null, '2', '1');
INSERT INTO `fill_question` VALUES ('10020', null, '2020-02-16 00:00:00', null, null, '分组交换网中，附加信息用来在网络中进行路由选择、() 和流量控制', '差错纠正  ', null, '4', '1');
INSERT INTO `fill_question` VALUES ('10021', null, '2020-02-16 00:00:00', null, null, '根据IEEE802模型的标准将数据链路层划分为LLC子层和 ()子层。', ' MAC ', null, '3', '1');
INSERT INTO `fill_question` VALUES ('10022', null, '2020-02-16 00:00:00', null, null, '据交换的路由信息的不同，路由算法可以分为两大类：  ()  和链路状态算法', '距离向量算法', null, '3', '1');
INSERT INTO `fill_question` VALUES ('10023', null, '2020-02-16 00:00:00', null, null, '假定某信道受奈氏准则限制的最高码元速率为2000码元/秒。如果采用振幅调制，把码元的振幅划分为16个不同等级来传送，那么可以获得的数据率为 () b/s。', '80000 ', null, '5', '1');
INSERT INTO `fill_question` VALUES ('10024', null, '2020-02-16 00:00:00', null, null, '交换型以太网系统中的 ()  ，以其为核心联接站点或者网段，端口之间帧的输入和输出已不再受到CSMA/CD媒体访问控制协议的约束。', '以太网交换器 ', null, '5', '1');
INSERT INTO `fill_question` VALUES ('10025', null, '2020-02-16 00:00:00', null, null, '局域网络参考模型是以 ()标准为基础的', 'IEEE802', null, '5', '1');
INSERT INTO `fill_question` VALUES ('10026', null, '2020-02-16 00:00:00', null, null, '路由器的核心是 () 。', ' 路由表', null, '3', '1');
INSERT INTO `fill_question` VALUES ('10027', null, '2020-02-16 00:00:00', null, null, '若 HDLC 帧数据段中出现比特串“ 01011111110 ”，则比特填充后的输出为()', '10111110110', null, '5', '1');
INSERT INTO `fill_question` VALUES ('10028', null, '2020-02-16 00:00:00', null, null, '数字调制的三种基本形式：移幅键控法ASK、 ()、移相键控法PSK', '移频键控法FSK', null, '5', '1');
INSERT INTO `fill_question` VALUES ('22e66bf88e3c4ccaa0f34b4cdbe40db7', null, '2020-05-25 16:15:04', null, null, 'aa', 'df ', '', '1', null);
INSERT INTO `fill_question` VALUES ('36107cfbe96a4c3abf2cb55d0708df1f', null, '2020-05-25 13:19:03', null, null, '常用的两种传输协议是()和()', 'TCP,UDP', null, '1', null);
INSERT INTO `fill_question` VALUES ('5a915ad82b1d4a4b97224a5816a7e819', null, '2020-05-26 13:24:30', null, null, '测试填空题，elementUI是基于()的框架', 'vue', '使用小写', '1', null);
INSERT INTO `fill_question` VALUES ('65ba823289b14f86ab31ebf606fa1762', null, '2020-05-25 16:10:20', null, null, '测试', '', '', null, null);
INSERT INTO `fill_question` VALUES ('68833ded6e0244ccb5fbdceeef692efd', null, '2020-05-25 16:12:46', null, null, '辅导辅导', '大幅度发', '', '2', null);

-- ----------------------------
-- Table structure for `judge_question`
-- ----------------------------
DROP TABLE IF EXISTS `judge_question`;
CREATE TABLE `judge_question` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `create_id` varchar(32) DEFAULT NULL COMMENT '题目创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '题目创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '题目更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '题目更新时间',
  `question_content` varchar(255) DEFAULT NULL COMMENT '题目内容',
  `question_answer` varchar(255) DEFAULT NULL COMMENT '题目答案',
  `question_explain` varchar(255) DEFAULT NULL COMMENT '题目解析',
  `question_level` int(2) DEFAULT NULL COMMENT '题目难度等级',
  `question_type` int(2) DEFAULT NULL COMMENT '题目分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='判断题';

-- ----------------------------
-- Records of judge_question
-- ----------------------------
INSERT INTO `judge_question` VALUES ('110001', null, '2020-02-16 00:00:00', null, null, '与有线网相比,无线网的数据传输率一般相对较慢', 'T', null, '1', '1');
INSERT INTO `judge_question` VALUES ('110002', null, '2020-02-16 00:00:00', null, null, 'OSI参考模型中,不同节点的同等层具有不同的功能', 'F', null, '1', '1');
INSERT INTO `judge_question` VALUES ('110003', null, '2020-02-16 00:00:00', null, null, '普通电脑不能作为服务器', 'F', null, '1', '1');
INSERT INTO `judge_question` VALUES ('110004', null, '2020-02-16 00:00:00', null, null, '没有网线的电脑不能连入互联网', 'F', null, '1', '1');
INSERT INTO `judge_question` VALUES ('110005', null, '2020-02-16 00:00:00', null, null, '网卡必须安装驱动程序', 'T', null, '2', '1');
INSERT INTO `judge_question` VALUES ('110006', null, '2020-02-16 00:00:00', null, null, 'UTP为屏蔽双绞线', 'F', null, '2', '1');
INSERT INTO `judge_question` VALUES ('110007', null, '2020-02-16 00:00:00', null, null, '网络接口卡又称为网卡,它是构成网络的基本部件', 'T', null, '2', '1');
INSERT INTO `judge_question` VALUES ('110008', null, '2020-02-16 00:00:00', null, null, '无线AP可以成倍地扩展网络覆盖范围', 'T', null, '3', '1');
INSERT INTO `judge_question` VALUES ('110009', null, '2020-02-16 00:00:00', null, null, 'SMTP是一组用于由源地址到目的地址传送邮件的协议', 'T', null, '4', '1');
INSERT INTO `judge_question` VALUES ('110010', null, '2020-02-16 00:00:00', null, null, '任务管理器可以关闭所有的进程', 'F', null, '3', '1');
INSERT INTO `judge_question` VALUES ('110011', null, '2020-02-16 00:00:00', null, null, '利用BT下载时,用户越多,下载速度越快', 'T', null, '2', '1');
INSERT INTO `judge_question` VALUES ('110012', null, '2020-02-16 00:00:00', null, null, 'INTERNET上向朋友发送电子邮件,必须知道对方的真实姓名和家庭住址', 'F', null, '1', '1');
INSERT INTO `judge_question` VALUES ('7e8f9415828a4fd1b71169d414dee3c2', null, '2020-05-25 15:26:37', null, null, '计算机可以直接识别C语言', 'F', '计算机直接识别的语言是机器语言', '1', null);
INSERT INTO `judge_question` VALUES ('e8ee05b4583f4124bbc8fc58329786b9', null, '2020-05-26 13:25:35', null, null, '测试判断题，vue是前端框架', 'T', '', '1', null);
INSERT INTO `judge_question` VALUES ('f6751aa596ce4a1fb9d8066619cd5c9e', null, '2020-05-25 16:15:27', null, null, 'bb', 'T', '', '2', null);

-- ----------------------------
-- Table structure for `multi_question`
-- ----------------------------
DROP TABLE IF EXISTS `multi_question`;
CREATE TABLE `multi_question` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `create_id` varchar(32) DEFAULT NULL COMMENT '题目创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '题目创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '题目更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '题目更新时间',
  `question_content` varchar(255) DEFAULT NULL COMMENT '题目内容',
  `choice_A` varchar(255) DEFAULT NULL COMMENT '选项A',
  `choice_B` varchar(255) DEFAULT NULL COMMENT '选项B',
  `choice_C` varchar(255) DEFAULT NULL COMMENT '选项C',
  `choice_D` varchar(255) DEFAULT NULL COMMENT '选项D',
  `question_answer` varchar(255) DEFAULT NULL COMMENT '题目答案',
  `question_explain` varchar(255) DEFAULT NULL COMMENT '题目解析',
  `question_level` int(2) DEFAULT NULL COMMENT '题目难度等级',
  `question_type` int(2) DEFAULT NULL COMMENT '题目分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='多选题';

-- ----------------------------
-- Records of multi_question
-- ----------------------------
INSERT INTO `multi_question` VALUES ('0a1ce184ec014d18abcb5a46777a57fc', null, '2020-05-26 13:27:05', null, null, '测试多选题', 'aaa', 'ggg', 'ddd', 'sss', 'A,B', '测试而已', '1', null);
INSERT INTO `multi_question` VALUES ('1', null, null, null, null, '以下说法正确的是？', '1+1=2', '1+1=3', 'C语言是高级程序', 'java是跨平台的语言', 'A,C,D', null, '1', '1');
INSERT INTO `multi_question` VALUES ('2', null, null, null, null, '高级语言包括？', '汇编', 'C语言', 'C++', 'java', 'B,C,D', null, '1', '1');
INSERT INTO `multi_question` VALUES ('61db08f1ef1547b68c7424ba89f6f47b', null, '2020-05-25 16:03:49', null, null, 'aa', 'a', 'b', 'c', 'd', 'A,B,D', 'fdff', '1', null);
INSERT INTO `multi_question` VALUES ('cd126c1ad13244a3a31681897c224a0d', null, '2020-05-26 12:07:09', null, null, '测试添加多选题', 'aa', 'bb', 'cc', 'dd', 'B,C', '测试而已', '1', null);

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `name` varchar(10) NOT NULL COMMENT '权限名称',
  `url` varchar(255) NOT NULL COMMENT '权限路径',
  `type` int(1) DEFAULT NULL COMMENT '鏉冮檺绫诲瀷',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父权限id',
  `sort` int(3) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('09179d47a21111eabcad5c93a27933da', '添加多选题', '/multi/add', '1', 'a5be7f34a20d11eabcad5c93a27933da', '13');
INSERT INTO `permission` VALUES ('1b90011ca21211eabcad5c93a27933da', '修改用户', '/user/update', '1', 'af552ba1a21111eabcad5c93a27933da', '21');
INSERT INTO `permission` VALUES ('213cedc1a20d11eabcad5c93a27933da', '添加考试记录', '/examRecord/add', '1', '561ce86ea20b11eabcad5c93a27933da', '7');
INSERT INTO `permission` VALUES ('2d58b933a21211eabcad5c93a27933da', '删除用户', '/user/delete', '1', 'af552ba1a21111eabcad5c93a27933da', '22');
INSERT INTO `permission` VALUES ('40af7ac1a20c11eabcad5c93a27933da', '进入答题页面', '/exam/answer', '1', '561ce86ea20b11eabcad5c93a27933da', '4');
INSERT INTO `permission` VALUES ('41495156a21111eabcad5c93a27933da', '试卷管理', '/paper', '0', '0', '14');
INSERT INTO `permission` VALUES ('54b9f00da21111eabcad5c93a27933da', '试卷列表', '/paper/list', '1', '41495156a21111eabcad5c93a27933da', '15');
INSERT INTO `permission` VALUES ('561ce86ea20b11eabcad5c93a27933da', '考试功能入口', '/exam', '0', '0', '1');
INSERT INTO `permission` VALUES ('77d7fc46a21111eabcad5c93a27933da', '随机组卷', '/paper/add', '1', '41495156a21111eabcad5c93a27933da', '16');
INSERT INTO `permission` VALUES ('8a40d006a21111eabcad5c93a27933da', '查询题库数量', '/paper/count', '1', '41495156a21111eabcad5c93a27933da', '17');
INSERT INTO `permission` VALUES ('8cfc091ba20c11eabcad5c93a27933da', '考试记录列表', '/examRecord/list', '1', '561ce86ea20b11eabcad5c93a27933da', '5');
INSERT INTO `permission` VALUES ('a48b57f7a21011eabcad5c93a27933da', '题库列表', '/question/list', '1', 'a5be7f34a20d11eabcad5c93a27933da', '9');
INSERT INTO `permission` VALUES ('a5be7f34a20d11eabcad5c93a27933da', '题库管理', '/question', '0', '0', '8');
INSERT INTO `permission` VALUES ('af552ba1a21111eabcad5c93a27933da', '用户管理', '/user', '0', '0', '18');
INSERT INTO `permission` VALUES ('b1c98433a20c11eabcad5c93a27933da', '考试记录详情', '/examRecord/view', '1', '561ce86ea20b11eabcad5c93a27933da', '6');
INSERT INTO `permission` VALUES ('c0ba58a8a21211eabcad5c93a27933da', '用户上传头像', '/user/upload', '1', 'af552ba1a21111eabcad5c93a27933da', '23');
INSERT INTO `permission` VALUES ('c536b109a21111eabcad5c93a27933da', '用户列表', '/user/list', '1', 'af552ba1a21111eabcad5c93a27933da', '19');
INSERT INTO `permission` VALUES ('c5569835a21011eabcad5c93a27933da', '添加填空题', '/fill/add', '1', 'a5be7f34a20d11eabcad5c93a27933da', '10');
INSERT INTO `permission` VALUES ('c627418ba20b11eabcad5c93a27933da', '考试列表', '/exam/list', '1', '561ce86ea20b11eabcad5c93a27933da', '2');
INSERT INTO `permission` VALUES ('db7a3f41a21111eabcad5c93a27933da', '添加用户', '/user/add', '1', 'af552ba1a21111eabcad5c93a27933da', '20');
INSERT INTO `permission` VALUES ('dbe34517a21011eabcad5c93a27933da', '添加判断题', '/judge/add', '1', 'a5be7f34a20d11eabcad5c93a27933da', '11');
INSERT INTO `permission` VALUES ('e9f431eba20b11eabcad5c93a27933da', '考试详情', '/exam/detail', '1', '561ce86ea20b11eabcad5c93a27933da', '3');
INSERT INTO `permission` VALUES ('f5da04a6a21011eabcad5c93a27933da', '添加单选题', '/single/add', '1', 'a5be7f34a20d11eabcad5c93a27933da', '12');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '瑙掕壊鍚嶇О',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '瑙掕壊鎻忚堪',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `role` VALUES ('2', 'ROLE_TEACHER', '老师');
INSERT INTO `role` VALUES ('3', 'ROLE_STUDENT', '学生');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '娑撳鏁璱d',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '瑙掕壊id',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '閺夊啴妾篿d',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('b9aac5eea21411eabcad5c93a27933da', '1', '09179d47a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacb9ea21411eabcad5c93a27933da', '1', '1b90011ca21211eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacc93a21411eabcad5c93a27933da', '1', '213cedc1a20d11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacd02a21411eabcad5c93a27933da', '1', '2d58b933a21211eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacd98a21411eabcad5c93a27933da', '1', '40af7ac1a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacdfaa21411eabcad5c93a27933da', '1', '41495156a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aace4da21411eabcad5c93a27933da', '1', '54b9f00da21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacec8a21411eabcad5c93a27933da', '1', '561ce86ea20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacf22a21411eabcad5c93a27933da', '1', '77d7fc46a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacf75a21411eabcad5c93a27933da', '1', '8a40d006a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aacfe8a21411eabcad5c93a27933da', '1', '8cfc091ba20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad03ba21411eabcad5c93a27933da', '1', 'a48b57f7a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad08ea21411eabcad5c93a27933da', '1', 'a5be7f34a20d11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad114a21411eabcad5c93a27933da', '1', 'af552ba1a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad167a21411eabcad5c93a27933da', '1', 'b1c98433a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad1d1a21411eabcad5c93a27933da', '1', 'c0ba58a8a21211eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad26fa21411eabcad5c93a27933da', '1', 'c536b109a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad2f1a21411eabcad5c93a27933da', '1', 'c5569835a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad34ca21411eabcad5c93a27933da', '1', 'c627418ba20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad3a7a21411eabcad5c93a27933da', '1', 'db7a3f41a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad41da21411eabcad5c93a27933da', '1', 'dbe34517a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad474a21411eabcad5c93a27933da', '1', 'e9f431eba20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('b9aad4c7a21411eabcad5c93a27933da', '1', 'f5da04a6a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e66e9a21411eabcad5c93a27933da', '2', '09179d47a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e6c43a21411eabcad5c93a27933da', '2', '213cedc1a20d11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e6d6fa21411eabcad5c93a27933da', '2', '40af7ac1a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e6df9a21411eabcad5c93a27933da', '2', '41495156a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e6ea7a21411eabcad5c93a27933da', '2', '54b9f00da21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e6f41a21411eabcad5c93a27933da', '2', '561ce86ea20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e701aa21411eabcad5c93a27933da', '2', '77d7fc46a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e70d3a21411eabcad5c93a27933da', '2', '8a40d006a21111eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e71f4a21411eabcad5c93a27933da', '2', '8cfc091ba20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e72a1a21411eabcad5c93a27933da', '2', 'a48b57f7a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e7314a21411eabcad5c93a27933da', '2', 'a5be7f34a20d11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e7396a21411eabcad5c93a27933da', '2', 'b1c98433a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e744ca21411eabcad5c93a27933da', '2', 'c5569835a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e74d2a21411eabcad5c93a27933da', '2', 'c627418ba20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e7554a21411eabcad5c93a27933da', '2', 'dbe34517a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e75daa21411eabcad5c93a27933da', '2', 'e9f431eba20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('eb3e7651a21411eabcad5c93a27933da', '2', 'f5da04a6a21011eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5848a21411eabcad5c93a27933da', '3', '213cedc1a20d11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5d18a21411eabcad5c93a27933da', '3', '40af7ac1a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5dcea21411eabcad5c93a27933da', '3', '561ce86ea20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5e4ca21411eabcad5c93a27933da', '3', '8cfc091ba20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5eeea21411eabcad5c93a27933da', '3', 'b1c98433a20c11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5f64a21411eabcad5c93a27933da', '3', 'c627418ba20b11eabcad5c93a27933da');
INSERT INTO `role_permission` VALUES ('f03c5fc7a21411eabcad5c93a27933da', '3', 'e9f431eba20b11eabcad5c93a27933da');

-- ----------------------------
-- Table structure for `single_question`
-- ----------------------------
DROP TABLE IF EXISTS `single_question`;
CREATE TABLE `single_question` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `create_id` varchar(32) DEFAULT NULL COMMENT '题目创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '题目创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '题目更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '题目更新时间',
  `question_content` varchar(255) DEFAULT NULL COMMENT '题目内容',
  `choice_A` varchar(255) DEFAULT NULL COMMENT '选项A',
  `choice_B` varchar(255) DEFAULT NULL COMMENT '选项B',
  `choice_C` varchar(255) DEFAULT NULL COMMENT '选项C',
  `choice_D` varchar(255) DEFAULT NULL COMMENT '选项D',
  `question_answer` varchar(255) DEFAULT NULL COMMENT '题目答案',
  `question_explain` varchar(255) DEFAULT NULL COMMENT '题目解析',
  `question_level` int(2) DEFAULT NULL COMMENT '题目难度等级',
  `question_type` int(2) DEFAULT NULL COMMENT '题目分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='单选题';

-- ----------------------------
-- Records of single_question
-- ----------------------------
INSERT INTO `single_question` VALUES ('120000', null, '2020-02-16 00:00:00', null, null, 'DNS 服务器和DHCP服务器的作用是（）', '将IP地址翻译为计算机名，为客户机分配IP地址', '将IP地址翻译为计算机名、解析计算机的MAC地址', '将计算机名翻译为IP地址、为客户机分配IP地址', '将计算机名翻译为IP地址、解析计算机的MAC地址', 'C', null, '1', '1');
INSERT INTO `single_question` VALUES ('120001', null, '2020-02-16 00:00:00', null, null, 'HTTP协议通常使用什么协议进行传输（）', 'ARP', 'DHCP', 'UDP', 'TCP', 'D', null, '1', '1');
INSERT INTO `single_question` VALUES ('120003', null, '2020-02-16 00:00:00', null, null, '查看DNS缓存记录的命令（）', 'ipconfig/displaydns', 'nslookup', 'ipconfig/release', 'ipconfig/flushdns', 'A', null, '1', '1');
INSERT INTO `single_question` VALUES ('120004', null, '2020-02-16 00:00:00', null, null, 'DHCP(        )报文的目的IP地址为255.255.255.255', 'DhcpDisover', 'DhcpOffer', 'DhcpAck', 'DhcpNack', 'A', null, '1', '1');
INSERT INTO `single_question` VALUES ('120005', null, '2020-02-16 00:00:00', null, null, '下列地址中，（  ）不是DHCP服务器分配的IP地址', '196.254.109.100', '169.254.12.42', '69.254.48.45', '96.254.54.15', 'B', null, '1', '1');
INSERT INTO `single_question` VALUES ('120006', null, '2020-02-16 00:00:00', null, null, 'DHCP通常可以为客户端自动配置哪些网络参数（）', 'IP，掩码，网关，DNS', 'IP，掩码，域名，SMTP', '网关，掩码，浏览器，FTP', 'IP，网关，DNS，服务器', 'A', null, '1', '1');
INSERT INTO `single_question` VALUES ('120007', null, '2020-02-16 00:00:00', null, null, 'DNS服务器在名称解析过程中正确的查询顺序为（）', '本地缓存记录→区域记录→转发域名服务器→根域名服务器', '区域记录→本地缓存记录→转发域名服务器→根域名服务器', '本地缓存记录→区域记录→根域名服务器→转发域名服务器', '区域记录→本地缓存记录→根域名服务器→转发域名服务器', 'A', null, '1', '1');
INSERT INTO `single_question` VALUES ('120008', null, '2020-02-16 00:00:00', null, null, '在TCP/IP协议中，序号小于（  ）的端口称为熟知端口(well-known port)。', '1024', '64', '256', '128', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120009', null, '2020-02-16 00:00:00', null, null, '在Internet上用TCP/IP播放视频，想用传输层的最快协议，以减少时延，要使用（ ）', 'UDP协议的低开销特性', 'UDP协议的高开销特性', 'TCP协议的低开销特性', 'TCP协议的高开销特性', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120010', null, '2020-02-16 00:00:00', null, null, '在TCP协议中采用（ ）来区分不同的应用进程', '端口号', 'IP地址', '协议类型', 'MAC地址', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120011', null, '2020-02-16 00:00:00', null, null, '可靠的传输协议中的“可靠”指的是（ ）', '使用面向连接的会话', '使用“尽力而为”的传输', '使用滑动窗口来维持可靠性', '使用确认重传机制来确保传输的数据不丢失', 'D', null, '2', '1');
INSERT INTO `single_question` VALUES ('120012', null, '2020-02-16 00:00:00', null, null, '假设拥塞窗口为50KB，接收窗口为80KB，TCP能够发送的最大字节数为（ ）', '50KB', '80KB', '130KB', '30KB', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120013', null, '2020-02-16 00:00:00', null, null, '主机A向主机B发送一个（SYN=1，seq=2000）的TCP报文，期望与主机B建立连接，若主机B接受连接请求，则主机B发送的正确有TCP报文可能是（ ）', '（SYN=0,ACK=0,seq=2001,ack=2001）', '（SYN=1,ACK=1,seq=2000,ack=2000）', '•	C.（SYN=1,ACK=1,seq=2001,ack=2001）', '（SYN=0,ACK=1,seq=2000,ack=2000）', 'C', null, '2', '1');
INSERT INTO `single_question` VALUES ('120014', null, '2020-02-16 00:00:00', null, null, '主机A向主机B连续发送了两个TCP报文段，其序号分别为70和100。试问： （1）第一个报文段携带了（）个字节的数据？', ' 70', '30', '100', '170', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120015', null, '2020-02-16 00:00:00', null, null, 'PCM脉码调制的过程（ ）', '采样、量化、编码', '量化、编码、采样', '编码、量化、采样', '采样、编码、量化', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120016', null, '2020-02-16 00:00:00', null, null, '若某采用4相位调制的通信链路的数据传输速率为2400bps，则该链路的波特率为（）', '600Baud', '1200Baud', '4800Baud', '9600Baud', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120017', null, '2020-02-16 00:00:00', null, null, '以下关于数据传输速率的描述中，错误的是( )', '数据传输速率表示每秒钟传输构成数据代码的二进制比特数', '对于二进制数据，数据传输速率为S=1/T (bps)', '常用的数据传输速率单位有: 1Mbps=1.024×106bps', '数据传输速率是描述数据传输系统性能的重要技术指标之一', 'C', null, '2', '1');
INSERT INTO `single_question` VALUES ('120018', null, '2020-02-16 00:00:00', null, null, '以下关于时分多路复用概念的描述中，错误的是.(  ).', '时分多路复用将线路使用的时间分成多个时间片', '时分多路复用分为同步时分多路复用与统计时分多路复用', '时分多路复用使用“帧”与数据链路层“帧”的概念、作用是不同的', '统计时分多路复用将时间片预先分配给各个信道', 'D', null, '2', '1');
INSERT INTO `single_question` VALUES ('120019', null, '2020-02-16 00:00:00', null, null, '1000BASE-T标准支持的传输介质是（）', '双绞线', '同轴电缆', '光纤', '无线电', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120020', null, '2020-02-16 00:00:00', null, null, '一个以太网交换机，读取整个数据帧，对数据帧进行差错校验后再转发出去，这种交换方式称为 （）', '直通交换', '无碎片交换', '无差错交换', '存储转发交换', 'D', null, '2', '1');
INSERT INTO `single_question` VALUES ('120021', null, '2020-02-16 00:00:00', null, null, '关于VLAN，下面的描述中正确的是（）', '一个新的交换机没有配置VLAN', '通过配置VLAN减少了冲突域的数量', '一个VLAN不能跨越多个交换机', '各个VLAN属于不同的广播域', 'D', null, '2', '1');
INSERT INTO `single_question` VALUES ('120022', null, '2020-02-16 00:00:00', null, null, '以太网协议中使用物理地址作用是什么？', '.用于不同子网中的主机进行通信', '作为第二层设备的唯一标识', '用于区别第二层第三层的协议数据单元', '保存主机可检测未知的远程设备', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120023', null, '2020-02-16 00:00:00', null, null, '以太网采用的CSMA/CD协议，当冲突发生时要通过二进制指数后退算法计算后退延时， 关于这个算法，以下论述中错误的是 （）', '冲突次数越多，后退的时间越短', '平均后退次数的多少与负载大小有关', '后退时延的平均值与负载大小有关', '重发次数达到一定极限后放弃发送', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120024', null, '2020-02-16 00:00:00', null, null, '以下关于交换机获取与其端口连接设备的MAC地址的叙述中，正确的是（）', '交换机从路由表中提取设备的MAC地址', '交换机检查端口流入分组的源地址', '交换机之间互相交换地址表', '网络管理员手工输入设备的MAC地址', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120025', null, '2020-02-16 00:00:00', null, null, '如果G (x）为11010010，以下4个CRC校验比特序列中只有哪个可能是正确的 ？', '1101011001', '101011011', '11011011', '1011001', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120026', null, '2020-02-16 00:00:00', null, null, '以下关于Ethernet物理地址的描述中，错误的是', 'Ethernet物理地址又叫做MAC地址', '48位的Ethernet物理地址允许分配的地址数达到247个', '网卡的物理地址写入主机的EPROM中', '每一块网卡的物理地址在全世界是唯一的', 'C', null, '2', '1');
INSERT INTO `single_question` VALUES ('120027', null, '2020-02-16 00:00:00', null, null, '下列帧类型中，不属于HDLC帧类型的是（）', '信息帧', '确认帧', '监控帧', '无编号帧', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120028', null, '2020-02-16 00:00:00', null, null, '通过交换机连接的一组站点，关于它们的广播域和冲突域说法正确的是（）', '组成一个冲突域，但不是一个广播域', '组成一个广播域，但不是一个冲突域', '组成一个冲突域，也是一个广播域', '既不一个冲突域，也不是一个广播域', 'B', null, '2', '1');
INSERT INTO `single_question` VALUES ('120029', null, '2020-02-16 00:00:00', null, null, '数据链路层的数据单位是（）', '帧', '字节', '比特', '分组', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120030', null, '2020-02-16 00:00:00', null, null, 'LAN参考模型可分为物理层、（ ）', 'MAC，LLC等三层', 'LLC，MHS等三层', 'MAC，FTAM等三层', 'LLC，VT等三层', 'A', null, '2', '1');
INSERT INTO `single_question` VALUES ('120031', null, '2020-02-16 00:00:00', null, null, '测试', 'A', 'B', 'C', 'D', 'B', '解析', '1', '1');
INSERT INTO `single_question` VALUES ('120032', null, '2020-02-16 00:00:00', null, null, 'DNS 服务器和DHCP服务器的作用是（）', 'A', 'B', 'C', 'D', 'B', '哦解析', '1', '1');
INSERT INTO `single_question` VALUES ('120033', null, '2020-02-16 00:00:00', null, null, '测试（）', '11', '22', '33', '44', 'A', '', '1', '1');
INSERT INTO `single_question` VALUES ('120034', null, '2020-02-16 00:00:00', null, null, '测试2（）', '1', '2', '3', '4', 'D', null, '1', '1');
INSERT INTO `single_question` VALUES ('45abc480afe843f5ba695d9db5da7143', null, '2020-05-25 16:16:06', null, null, 'bb', 'fdfd', 'fdfdf', 'dfdf', 'fdf', 'B', '', '2', null);
INSERT INTO `single_question` VALUES ('64b2d4957d0040e4ac3ec7c801834964', null, '2020-05-26 13:26:21', null, null, '测试单选题', 'aaa', 'ggg', 'ddd', 'sss', 'A', '测试而已', '1', null);
INSERT INTO `single_question` VALUES ('837a0f0acef34db59451dbbf5ecdee21', null, '2020-05-25 15:29:21', null, null, '计算机可以直接识别什么语言？', 'C语言', 'C++', '机器语言', 'Java', 'C', '计算机直接识别的语言是机器语言', '1', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `photo_url` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `enable` int(1) DEFAULT NULL COMMENT '是否可用：1可用，0不可用',
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('62102e2c592344fa8d3bfcee81a5c47c', 'sam', '$2a$10$.3WcqJqovzfGnac9Es8/uuo7kA.TQCUsEEsMUQ33N7iTZmjQqX0HS', 'sam@qq.com', 'http://localhost:8888/api/file/f860578d-9171-4c94-90d0-02994c800fef.jpg', '1', '2020-05-30 13:04:13', null);
INSERT INTO `user` VALUES ('67eb71f1091911eab9205c93a27933da', 'admin', '$2a$10$t37oOKd93ElclkIASbt/eu0Y9kWeH/VMyZ1pOGkfiwBVKQ57rbgEq', 'admin@163.com', 'http://localhost:8888/api/file/56a662aa-c8a3-4eee-83b4-3963327a7c2c.jpg', '1', '2019-11-18 23:17:12', '2020-04-26 15:24:34');
INSERT INTO `user` VALUES ('c115859b93654616bfb4c542bd6fdd06', 'zs', '$2a$10$loGkYXAa942yO0aTIXUvrOhZWVqCNxWW1nPSZCmm.zefW2zx3bgMS', 'zs@qq.com', 'http://localhost:8888/api/file/6724dc33-4ba2-48ae-908d-625bb5af7872.jpg', '0', '2020-05-28 15:42:20', null);
INSERT INTO `user` VALUES ('c54880d2de294167a83c97d7f6b94097', 'qiuxinfa', '$2a$10$/JSpdeT22TET3ea6dRcUw.MhbTnY.yevh71ji5V4xsUs.NOWqSH/i', 'qiuxinfa@qq.com', 'http://localhost:8888/api/file/4297c74d-d735-412e-b205-6c9e09b3f6d2.jpg', '1', '2020-05-28 14:53:48', null);

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户-角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('226230da091911eab9205c93a27933da', '67eb71f1091911eab9205c93a27933da', '1');
INSERT INTO `user_role` VALUES ('5ad5fa41a0b743bb94379bffd39259e2', '62102e2c592344fa8d3bfcee81a5c47c', '2');
INSERT INTO `user_role` VALUES ('69d762d0fba1405da865aaab9654f366', 'c115859b93654616bfb4c542bd6fdd06', '3');
INSERT INTO `user_role` VALUES ('86fffc22a61644b6b5e47f9decb348b5', 'f9b78cf8b0664fdb93fb1138c84437fe', '2');
INSERT INTO `user_role` VALUES ('cd83f6df1f07441daaff1cc2ef3a666a', 'c54880d2de294167a83c97d7f6b94097', '3');
