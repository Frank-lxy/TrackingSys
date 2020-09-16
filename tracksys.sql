/*
Navicat MySQL Data Transfer

Source Server         : sss
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tracksys

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-09-11 17:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for assessitem
-- ----------------------------
DROP TABLE IF EXISTS `assessitem`;
CREATE TABLE `assessitem` (
  `assessItemId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价项id',
  `assessItemName` varchar(255) DEFAULT NULL COMMENT '评价项名称',
  PRIMARY KEY (`assessItemId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assessitem
-- ----------------------------
INSERT INTO `assessitem` VALUES ('1', '能力');
INSERT INTO `assessitem` VALUES ('2', '积极性');
INSERT INTO `assessitem` VALUES ('3', '沟通交流');
INSERT INTO `assessitem` VALUES ('4', '人品');
INSERT INTO `assessitem` VALUES ('5', '性格');
INSERT INTO `assessitem` VALUES ('6', '自学能力');
INSERT INTO `assessitem` VALUES ('7', '审美');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `classId` int(11) NOT NULL AUTO_INCREMENT COMMENT '班期id',
  `clazz` varchar(255) DEFAULT NULL COMMENT '班期名称',
  `teacherName` varchar(255) DEFAULT NULL COMMENT '授课教师姓名',
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '金桥工程039期', '周玉飞');
INSERT INTO `clazz` VALUES ('2', '金桥工程040期', '李荣');
INSERT INTO `clazz` VALUES ('3', '金桥工程041期', '王茹');
INSERT INTO `clazz` VALUES ('4', '金桥工程042期', '周玉飞');
INSERT INTO `clazz` VALUES ('5', '金桥工程043期', '李荣');
INSERT INTO `clazz` VALUES ('6', '金桥工程044期', '王茹');
INSERT INTO `clazz` VALUES ('7', '金桥工程045期', '周玉飞');
INSERT INTO `clazz` VALUES ('8', '金桥工程046期', '谢静');
INSERT INTO `clazz` VALUES ('9', '金桥工程047期', '李荣');
INSERT INTO `clazz` VALUES ('10', '金桥工程048期', '王茹');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `courseName` varchar(255) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'HTML笔试');
INSERT INTO `course` VALUES ('2', 'Oracle笔试');
INSERT INTO `course` VALUES ('3', 'JS笔试');
INSERT INTO `course` VALUES ('4', 'java基础笔试');
INSERT INTO `course` VALUES ('5', 'java高级笔试');
INSERT INTO `course` VALUES ('6', 'L1面试');

-- ----------------------------
-- Table structure for coursesel
-- ----------------------------
DROP TABLE IF EXISTS `coursesel`;
CREATE TABLE `coursesel` (
  `courseId` int(11) NOT NULL COMMENT '课程id',
  `classId` int(11) NOT NULL COMMENT '班期id',
  PRIMARY KEY (`courseId`,`classId`),
  KEY `fk_classId_coursesel` (`classId`),
  CONSTRAINT `coursesel_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `clazz` (`classId`),
  CONSTRAINT `coursesel_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coursesel
-- ----------------------------
INSERT INTO `coursesel` VALUES ('1', '1');
INSERT INTO `coursesel` VALUES ('2', '1');
INSERT INTO `coursesel` VALUES ('3', '1');
INSERT INTO `coursesel` VALUES ('4', '1');
INSERT INTO `coursesel` VALUES ('5', '1');
INSERT INTO `coursesel` VALUES ('6', '1');
INSERT INTO `coursesel` VALUES ('1', '2');
INSERT INTO `coursesel` VALUES ('2', '2');
INSERT INTO `coursesel` VALUES ('3', '2');
INSERT INTO `coursesel` VALUES ('4', '2');
INSERT INTO `coursesel` VALUES ('5', '2');
INSERT INTO `coursesel` VALUES ('6', '2');
INSERT INTO `coursesel` VALUES ('1', '3');
INSERT INTO `coursesel` VALUES ('2', '3');
INSERT INTO `coursesel` VALUES ('3', '3');
INSERT INTO `coursesel` VALUES ('4', '3');
INSERT INTO `coursesel` VALUES ('5', '3');
INSERT INTO `coursesel` VALUES ('6', '3');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `departmentName` varchar(255) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'java开发部');
INSERT INTO `department` VALUES ('2', '前端开发部');
INSERT INTO `department` VALUES ('3', 'UI设计部');
INSERT INTO `department` VALUES ('4', '会计部');
INSERT INTO `department` VALUES ('5', '销售部');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `jobId` int(11) NOT NULL AUTO_INCREMENT COMMENT '职务表',
  `jobName` varchar(255) DEFAULT NULL COMMENT '职务名称',
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '软件开发工程师');
INSERT INTO `job` VALUES ('2', 'java开发工程师');
INSERT INTO `job` VALUES ('3', '售前');
INSERT INTO `job` VALUES ('4', '会计');
INSERT INTO `job` VALUES ('5', 'UI美化');
INSERT INTO `job` VALUES ('6', '前端设计工程师');
INSERT INTO `job` VALUES ('7', '售后');

-- ----------------------------
-- Table structure for mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
  `markId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评分项id',
  `studentId` int(11) DEFAULT NULL COMMENT '学员id',
  `assessItemId` int(11) DEFAULT NULL COMMENT '评价项id',
  `stageId` int(11) DEFAULT NULL COMMENT '阶段id',
  `mark` double DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`markId`),
  KEY `fk_studentId_mark` (`studentId`),
  KEY `fk_assessItemId_mark` (`assessItemId`),
  KEY `fk_stageId_mark` (`stageId`),
  CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`assessItemId`) REFERENCES `assessitem` (`assessItemId`),
  CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`stageId`) REFERENCES `stage` (`stageId`),
  CONSTRAINT `mark_ibfk_3` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mark
-- ----------------------------

-- ----------------------------
-- Table structure for sassess
-- ----------------------------
DROP TABLE IF EXISTS `sassess`;
CREATE TABLE `sassess` (
  `saId` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校评价id',
  `studentId` int(11) DEFAULT NULL COMMENT '学员id',
  `classId` int(11) DEFAULT NULL COMMENT '班期id',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `evaluate` double DEFAULT NULL COMMENT '整体评价分数',
  `assess` varchar(255) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`saId`),
  KEY `fk_classId_sassess` (`classId`),
  KEY `fk_studentId_sassess` (`studentId`),
  CONSTRAINT `sassess_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `clazz` (`classId`),
  CONSTRAINT `sassess_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sassess
-- ----------------------------

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `studentId` int(11) NOT NULL COMMENT '学员id',
  `courseId` int(11) NOT NULL COMMENT '课程id',
  `score` double DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`studentId`,`courseId`),
  KEY `fk_courseId_score` (`courseId`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`),
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------

-- ----------------------------
-- Table structure for stage
-- ----------------------------
DROP TABLE IF EXISTS `stage`;
CREATE TABLE `stage` (
  `stageId` int(11) NOT NULL AUTO_INCREMENT COMMENT '阶段id',
  `stageName` varchar(255) DEFAULT NULL COMMENT '阶段名称',
  PRIMARY KEY (`stageId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stage
-- ----------------------------
INSERT INTO `stage` VALUES ('1', '转正');
INSERT INTO `stage` VALUES ('2', '一年');
INSERT INTO `stage` VALUES ('3', '两年');
INSERT INTO `stage` VALUES ('4', '三年');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `homeTown` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `marriage` varchar(6) DEFAULT NULL COMMENT '婚否',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `identityNum` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `graduate` varchar(255) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `classId` int(11) DEFAULT NULL COMMENT '班期id',
  `managerId` int(11) DEFAULT NULL COMMENT '项目经理id',
  `hiredate` date DEFAULT NULL COMMENT '入职日期',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`studentId`),
  KEY `fk_classId_classId` (`classId`),
  KEY `fk_managerId_userId` (`managerId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `clazz` (`classId`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`managerId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '鹿康', '男', '汉族', '1995-07-23', '山东菏泽', '未婚', '15963660998', '370786199507231932', '山东大学', '机械工程', null, null, null, '2019-03-21', null);
INSERT INTO `student` VALUES ('2', '刘凯', '男', '汉族', '1995-10-11', '山东枣庄', '未婚', '13837069876', '370933199510110973', '山东大学', '机械设计制造', null, null, null, '2019-03-21', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` int(1) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '1');
INSERT INTO `user` VALUES ('2', '王茹', '121212', '2');
INSERT INTO `user` VALUES ('3', '李荣', '123123', '2');
INSERT INTO `user` VALUES ('4', '谢静', '123412', '2');
INSERT INTO `user` VALUES ('5', '周玉飞', '123451', '2');
INSERT INTO `user` VALUES ('6', '徐磊', '123456', '3');
INSERT INTO `user` VALUES ('7', '王超', '123443', '3');

-- ----------------------------
-- Table structure for wassess
-- ----------------------------
DROP TABLE IF EXISTS `wassess`;
CREATE TABLE `wassess` (
  `waId` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作评价表id',
  `studentId` int(11) DEFAULT NULL COMMENT '学员id',
  `managerName` varchar(255) DEFAULT NULL COMMENT '项目经理名称',
  `departmentId` int(11) DEFAULT NULL COMMENT '部门id',
  `jobId` int(11) DEFAULT NULL COMMENT '职务id',
  `assessItemId` int(11) DEFAULT NULL COMMENT '评价项id',
  `evaluate` double DEFAULT NULL COMMENT '整体评价分数',
  `assess` varchar(255) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`waId`),
  KEY `fk_studentId_wassess` (`studentId`),
  KEY `fk_departmentId_wassess` (`departmentId`),
  KEY `fk_jobId_wassess` (`jobId`),
  KEY `fk_assessItemId_wassess` (`assessItemId`),
  CONSTRAINT `wassess_ibfk_1` FOREIGN KEY (`assessItemId`) REFERENCES `assessitem` (`assessItemId`),
  CONSTRAINT `wassess_ibfk_2` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`),
  CONSTRAINT `wassess_ibfk_3` FOREIGN KEY (`jobId`) REFERENCES `job` (`jobId`),
  CONSTRAINT `wassess_ibfk_4` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wassess
-- ----------------------------
