-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: streamMedia
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `SM_Users`
--

DROP TABLE IF EXISTS `SM_Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SM_Users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(200) NOT NULL,
  `first_name` varchar(80) DEFAULT NULL,
  `last_name` varchar(90) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` char(15) DEFAULT NULL,
  `picture` varchar(300) DEFAULT NULL,
  `biography` text,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SM_Users`
--

LOCK TABLES `SM_Users` WRITE;
/*!40000 ALTER TABLE `SM_Users` DISABLE KEYS */;
INSERT INTO `SM_Users` VALUES (2,'mary','mary@streammedia.com','12345','Mary','Smith',NULL,NULL,NULL,NULL,'2020-02-12 06:00:00','2020-02-12 06:00:00'),(3,'ian','ian@madisoncollege.edu','12345','Ian','Declan',NULL,NULL,NULL,NULL,'2020-02-12 06:00:00','2020-02-12 06:00:00'),(4,'kjames','kjames@madisoncollege.edu','12345','King','James',NULL,NULL,NULL,NULL,'2020-02-12 06:00:00','2020-02-12 18:10:36'),(5,'ella','ella@streammedia.com','12345','Ella','Ishimwe',NULL,NULL,NULL,NULL,'2020-02-12 06:00:00','2020-02-12 06:00:00'),(6,'ian','iam@madisoncollege.edu','1234',NULL,NULL,NULL,NULL,NULL,NULL,'2020-02-14 06:00:00','2020-02-14 06:00:00'),(7,'ian','iam@madisoncollege.edu','1234',NULL,NULL,NULL,NULL,NULL,NULL,'2020-02-14 06:00:00','2020-02-14 06:00:00'),(8,'ian','iam@madisoncollege.edu','1234',NULL,NULL,NULL,NULL,NULL,NULL,'2020-02-14 06:00:00','2020-02-14 06:00:00'),(9,'ian','iam@madisoncollege.edu','1234',NULL,NULL,NULL,NULL,NULL,NULL,'2020-02-14 06:00:00','2020-02-14 06:00:00'),(10,'atorrence','atorrance@streammedia.com','12345','Aria','Torrence',NULL,NULL,NULL,NULL,'2020-02-15 06:00:00','2020-02-15 06:00:00');
/*!40000 ALTER TABLE `SM_Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-16 14:13:42
