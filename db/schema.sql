CREATE DATABASE /*!32312 IF NOT EXISTS*/ `healthtracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `healthtracker`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: healthtracker
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 /*!40101 SET NAMES utf8 */;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_log` (
  `activity_log_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `person_id` int NOT NULL,
  `duration` float,
  `calories_burned` float,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`activity_log_id`),
  KEY `activity_id_activity_log_idx` (`activity_id`),
  KEY `person_id_activity_log_idx` (`person_id`),
  CONSTRAINT `activity_id_activity_log` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_id_activity_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bmi_table` (
  `bmi_range_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bmi_range_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `food_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255),
  `calories` float,
  `carbs` float,
  `proteins` float,
  `fats` float,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_log` (
  `food_log_id` int NOT NULL AUTO_INCREMENT,
  `food_id` int NOT NULL,
  `person_id` int NOT NULL,
  `quantity` float NOT NULL DEFAULT '1',
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`food_log_id`),
  KEY `food_id_food_log_idx` (`food_id`),
  KEY `person_id_food_log_idx` (`person_id`),
  CONSTRAINT `food_id_food_log` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_id_food_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `height_log` (
  `height_log_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `unit` mediumtext NOT NULL,
  `amount` float NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`height_log_id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `person_id_height_log` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255),
  `gender` varchar(255),
  `birth_date` date,
  `goal_weight` float,
  `goal_calories` float,
  `activity_level` varchar(255),
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `person_name_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
 /*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weight_log` (
  `weight_log_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `unit` mediumtext NOT NULL,
  `amount` float NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`weight_log_id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_log`
--

LOCK TABLES `weight_log` WRITE;
/*!40000 ALTER TABLE `weight_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `weight_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'healthtracker'
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
