CREATE DATABASE  IF NOT EXISTS `phone_repair` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `phone_repair`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: phone_repair
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_account`
--

DROP TABLE IF EXISTS `tb_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_account` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL COMMENT '用户名',
  `admin_pwd` varchar(100) NOT NULL COMMENT '密码',
  `admin_role` int DEFAULT '0' COMMENT '角色 0：管理员 1：前台接待 2：维修人员 3：检测人员',
  `admin_state` int DEFAULT '0' COMMENT '状态 0：正常（默认） 1：锁定 2：注销',
  PRIMARY KEY (`aid`),
  UNIQUE KEY `admin_name_UNIQUE` (`admin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_account`
--

LOCK TABLES `tb_account` WRITE;
/*!40000 ALTER TABLE `tb_account` DISABLE KEYS */;
INSERT INTO `tb_account` VALUES (1,'雷军','e10adc3949ba59abbe56e057f20f883e',0,0),(4,'金凡','96e79218965eb72c92a549dd5a330112',2,0),(5,'卢伟冰','9db06bcff9248837f86d1a6bcf41c9e7',1,1),(6,'余承东','9db06bcff9248837f86d1a6bcf41c9e7',3,0);
/* 雷军密码：123456 */
/*!40000 ALTER TABLE `tb_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_factory`
--

DROP TABLE IF EXISTS `tb_factory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_factory` (
  `fac_id` int NOT NULL AUTO_INCREMENT,
  `fac_name` varchar(45) DEFAULT NULL,
  `fac_item` varchar(45) DEFAULT NULL,
  `fac_phone` varchar(45) DEFAULT NULL,
  `fac_state` int DEFAULT NULL,
  PRIMARY KEY (`fac_id`),
  UNIQUE KEY `fac_id_UNIQUE` (`fac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_factory`
--

LOCK TABLES `tb_factory` WRITE;
/*!40000 ALTER TABLE `tb_factory` DISABLE KEYS */;
INSERT INTO `tb_factory` VALUES (1,'供应商1','电池','12345678910',2),(2,'供应商2','摄像头','09876543210',1),(3,'12333','外屏','1112223334',2);
/*!40000 ALTER TABLE `tb_factory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_fittings`
--

DROP TABLE IF EXISTS `tb_fittings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_fittings` (
  `fit_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `fit_name` varchar(20) DEFAULT NULL COMMENT '配件名',
  `fit_no` varchar(45) DEFAULT NULL COMMENT '配件编号',
  `fit_qty` int DEFAULT NULL COMMENT '库存数量',
  `fit_factory` varchar(100) DEFAULT NULL COMMENT '原产地',
  PRIMARY KEY (`fit_id`),
  UNIQUE KEY `fit_no_UNIQUE` (`fit_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_fittings`
--

LOCK TABLES `tb_fittings` WRITE;
/*!40000 ALTER TABLE `tb_fittings` DISABLE KEYS */;
INSERT INTO `tb_fittings` VALUES (1,'外屏','001',98,'深圳'),(2,'内屏','002',120,'华强北'),(3,'摄像头','003',99,'华强北'),(4,'电池','004',100,'中关村'),(5,'主板','005',100,'美国');
/*!40000 ALTER TABLE `tb_fittings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_task`
--

DROP TABLE IF EXISTS `tb_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_task` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `cus_name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `cus_phone` varchar(20) DEFAULT NULL COMMENT '客户电话',
  `service_item` varchar(20) DEFAULT NULL COMMENT '维修项目',
  `task_no` varchar(20) DEFAULT NULL COMMENT '任务编号',
  `task_time` datetime DEFAULT NULL COMMENT '任务时间',
  `task_state` int DEFAULT '0' COMMENT '任务状态 0：录单（默认） 1：维修中 2：取机中',
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `task_no_UNIQUE` (`task_no`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_task`
--

LOCK TABLES `tb_task` WRITE;
/*!40000 ALTER TABLE `tb_task` DISABLE KEYS */;
INSERT INTO `tb_task` VALUES (19,'客户1','11122223333','外屏','T-231226-214101437','2023-12-26 21:41:01',2),(20,'客户2','33322221111','外屏','T-231226-214122584','2023-12-26 21:41:22',2),(22,'客户1','11122223333','摄像头','T-231227-214101437','2023-12-27 21:41:01',2);
/*!40000 ALTER TABLE `tb_task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-29  9:54:44
