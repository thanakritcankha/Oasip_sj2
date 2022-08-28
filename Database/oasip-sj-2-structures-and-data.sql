

-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: oasip
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `oasip` ;

-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oasip` DEFAULT CHARACTER SET utf8mb3 ;
USE `oasip` ;

--
-- Table structure for table `eventcategories`
--

DROP TABLE IF EXISTS `eventcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventcategories` (
  `eventCategoryId` int NOT NULL AUTO_INCREMENT,
  `eventCategoryName` varchar(100) NOT NULL,
  `eventCategoryDescription` varchar(500) DEFAULT NULL,
  `eventCategoryDuration` int NOT NULL,
  PRIMARY KEY (`eventCategoryId`),
  UNIQUE KEY `eventCategoryName_UNIQUE` (`eventCategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventcategories`
--

LOCK TABLES `eventcategories` WRITE;
/*!40000 ALTER TABLE `eventcategories` DISABLE KEYS */;
INSERT INTO `eventcategories` VALUES (1,'Project Management Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดงระหว่างขอคำปรึกษา',30),(2,'Devops/Infra Clinic','Use this event category for DevOps/Infra clinic.',20),(3,'Database Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ใน  วิชา INT221 integrated project I',15),(4,'Client-side Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ใน  วิชา INT221 integrated project I',30),(5,'Server-side clinic','',30);
/*!40000 ALTER TABLE `eventcategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `eventId` int NOT NULL AUTO_INCREMENT,
  `bookingName` varchar(100) NOT NULL,
  `bookingEmail` varchar(255) NOT NULL,
  `eventStartTime` datetime NOT NULL,
  `eventDuration` int NOT NULL,
  `eventNotes` text,
  `EventCategory_id` int NOT NULL,
  `user_Id` int NOT NULL,
  PRIMARY KEY (`eventId`),
  KEY `fk_Event_EventCategory_idx` (`EventCategory_id`),
  KEY `fk_events_users1_idx` (`user_Id`),
  CONSTRAINT `fk_Events_EventCategories` FOREIGN KEY (`EventCategory_id`) REFERENCES `eventcategories` (`eventCategoryId`),
  CONSTRAINT `fk_events_users1` FOREIGN KEY (`user_Id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` ENUM('admin', 'lecturer', 'student') NOT NULL DEFAULT 'student',
  `createOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
-- INSERT INTO `users` VALUES (1,'OASIP ADMIN','oasip.admin@kmutt.ac.th','123456','admin','2022-07-31 17:00:00','2022-07-31 17:00:00'),(2,'Somchai Jaidee','somchai.jai@kmutt.ac.th','123456','lecturer','2022-08-08 08:00:00','2022-08-08 08:00:00'),(3,'Komkrid Rakdee','komkrid.rak@mail.kmutt.ac.th','123456','student','2022-08-08 08:00:01','2022-08-08 08:00:01'),(4,'สมเกียรติ ขยันเรียน','somkiat.kay@kmutt.ac.th','123456','student','2022-08-16 02:00:00','2022-08-16 02:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-20  0:19:11