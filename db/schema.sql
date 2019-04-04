CREATE DATABASE  IF NOT EXISTS `health_tracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `health_tracker`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: health_tracker
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `activity_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_log`
--

DROP TABLE IF EXISTS `activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity_log` (
  `activity_log_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `duration` float NOT NULL,
  `calories_burned` float NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`activity_log_id`),
  KEY `activity_id_activity_log_idx` (`activity_id`),
  KEY `person_id_activity_log_idx` (`person_id`),
  CONSTRAINT `activity_id_activity_log` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_id_activity_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_log`
--

LOCK TABLES `activity_log` WRITE;
/*!40000 ALTER TABLE `activity_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmi_table`
--

DROP TABLE IF EXISTS `bmi_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bmi_table` (
  `bmi_range_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bmi_range_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmi_table`
--

LOCK TABLES `bmi_table` WRITE;
/*!40000 ALTER TABLE `bmi_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmi_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `food` (
  `food_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `calories` float NOT NULL,
  `proteins` float NOT NULL,
  `fats` float NOT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_log`
--

DROP TABLE IF EXISTS `food_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `food_log` (
  `food_log_id` bigint(20) NOT NULL,
  `food_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `quantity` float NOT NULL DEFAULT '1',
  `update_at` datetime NOT NULL,
  PRIMARY KEY (`food_log_id`),
  KEY `food_id_food_log_idx` (`food_id`),
  KEY `person_id_food_log_idx` (`person_id`),
  CONSTRAINT `food_id_food_log` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_id_food_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_log`
--

LOCK TABLES `food_log` WRITE;
/*!40000 ALTER TABLE `food_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `food_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `height_log`
--

DROP TABLE IF EXISTS `height_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `height_log` (
  `height_log_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `unit` mediumtext NOT NULL,
  `amount` float NOT NULL,
  `update_at` datetime NOT NULL,
  PRIMARY KEY (`height_log_id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `person_id_height_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `height_log`
--

LOCK TABLES `height_log` WRITE;
/*!40000 ALTER TABLE `height_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `height_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL DEFAULT '10101',
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  `current_weight_id` bigint(20) NOT NULL,
  `current_height_id` bigint(20) NOT NULL,
  `goal_weight` float NOT NULL,
  `goal_calories` float NOT NULL,
  `activity_level` varchar(255) NOT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `person_name_idx` (`name`),
  KEY `current_weight_id_person_idx` (`current_weight_id`),
  KEY `current_height_id_person_idx` (`current_height_id`),
  CONSTRAINT `current_height_id_person` FOREIGN KEY (`current_height_id`) REFERENCES `height_log` (`height_log_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `current_weight_id_person` FOREIGN KEY (`current_weight_id`) REFERENCES `weight_log` (`weight_log_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='								';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight_log`
--

DROP TABLE IF EXISTS `weight_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `weight_log` (
  `weight_log_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `unit` mediumtext NOT NULL,
  `amount` float NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`weight_log_id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_log`
--

LOCK TABLES `weight_log` WRITE;
/*!40000 ALTER TABLE `weight_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `weight_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'health_tracker'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-03 17:47:39
