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
-- Table structure for table `Crew`
--

DROP TABLE IF EXISTS `Crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Crew` (
  `crew_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(70) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(120) DEFAULT NULL,
  `profession` varchar(100) NOT NULL,
  `biography` text,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  PRIMARY KEY (`crew_id`),
  KEY `Crew_User_user_id_fk` (`user`),
  CONSTRAINT `Crew_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Crew`
--

LOCK TABLES `Crew` WRITE;
/*!40000 ALTER TABLE `Crew` DISABLE KEYS */;
INSERT INTO `Crew` VALUES (1,'Lisa','Larson','llarson@example.com','Student','This is biography for Lisa.','2020-03-04 06:00:00','2020-03-05 06:00:00',2);
/*!40000 ALTER TABLE `Crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Film`
--

DROP TABLE IF EXISTS `Film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Film` (
  `film_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `duration` time NOT NULL,
  `director` varchar(120) NOT NULL,
  `pub_date` timestamp NOT NULL,
  `episode` int DEFAULT NULL,
  `link` varchar(180) DEFAULT NULL,
  `video` varchar(200) DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `summary` text NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  PRIMARY KEY (`film_id`),
  UNIQUE KEY `title_UNIQUE` (`title`,`director`),
  KEY `Film_User_user_id_fk` (`user`),
  CONSTRAINT `Film_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Film`
--

LOCK TABLES `Film` WRITE;
/*!40000 ALTER TABLE `Film` DISABLE KEYS */;
/*!40000 ALTER TABLE `Film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilmCrew`
--

DROP TABLE IF EXISTS `FilmCrew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FilmCrew` (
  `film_id` int NOT NULL,
  `crew_id` int NOT NULL,
  PRIMARY KEY (`crew_id`,`film_id`),
  KEY `FilmCrew_Film_fk` (`film_id`),
  CONSTRAINT `FilmCrew_Crew_fk` FOREIGN KEY (`crew_id`) REFERENCES `Crew` (`crew_id`),
  CONSTRAINT `FilmCrew_Film_fk` FOREIGN KEY (`film_id`) REFERENCES `Film` (`film_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilmCrew`
--

LOCK TABLES `FilmCrew` WRITE;
/*!40000 ALTER TABLE `FilmCrew` DISABLE KEYS */;
/*!40000 ALTER TABLE `FilmCrew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilmGenre`
--

DROP TABLE IF EXISTS `FilmGenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FilmGenre` (
  `genre_id` int NOT NULL,
  `film_id` int NOT NULL,
  PRIMARY KEY (`genre_id`,`film_id`),
  KEY `FilmGenre_Film` (`film_id`),
  CONSTRAINT `FilmGenre_Film` FOREIGN KEY (`film_id`) REFERENCES `Film` (`film_id`),
  CONSTRAINT `FilmGenre_Genre` FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilmGenre`
--

LOCK TABLES `FilmGenre` WRITE;
/*!40000 ALTER TABLE `FilmGenre` DISABLE KEYS */;
/*!40000 ALTER TABLE `FilmGenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Genre`
--

DROP TABLE IF EXISTS `Genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Genre` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`genre_id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
INSERT INTO `Genre` VALUES (1,'Romantics','This is Love related films','2020-03-04 06:00:00','2020-03-04 06:00:00'),(2,'Horror','These are scary movies. Children should not watch them.More Data.','2020-03-04 06:00:00','2020-03-04 06:00:00');
/*!40000 ALTER TABLE `Genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `created_at` timestamp NOT NULL,
  `username` varchar(120) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  KEY `Role_User_username_fk` (`username`),
  CONSTRAINT `Role_User_username_fk` FOREIGN KEY (`username`) REFERENCES `SM_Users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'user','2020-02-16 06:00:00','ian','2020-03-02 06:00:00'),(2,'admin','2020-02-14 05:00:00','admin','2020-03-09 05:00:00');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SM_Users`
--

DROP TABLE IF EXISTS `SM_Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SM_Users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(120) NOT NULL,
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SM_Users`
--

LOCK TABLES `SM_Users` WRITE;
/*!40000 ALTER TABLE `SM_Users` DISABLE KEYS */;
INSERT INTO `SM_Users` VALUES (1,'ian','ian@madisoncollege.edu','12345','Ian','Declan','1995-06-12','Male','','This is a Test.TestMore and More and more','2020-02-17 04:03:49','2020-03-02 06:00:00'),(2,'admin','admin@streammedia.com','12345','Jane','Doe','2005-07-27','Female','','This is an admin Profile.This is an update','2020-02-14 05:00:00','2020-03-09 05:00:00');
/*!40000 ALTER TABLE `SM_Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trailer`
--

DROP TABLE IF EXISTS `Trailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Trailer` (
  `trailer_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author` varchar(150) DEFAULT NULL,
  `duration` time DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `pub_date` timestamp NOT NULL,
  `links` varchar(150) DEFAULT NULL,
  `video` varchar(200) DEFAULT NULL,
  `summary` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  PRIMARY KEY (`trailer_id`),
  UNIQUE KEY `title_UNIQUE` (`title`,`author`),
  KEY `tariler_User_user_id_fk` (`user`),
  CONSTRAINT `tariler_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trailer`
--

LOCK TABLES `Trailer` WRITE;
/*!40000 ALTER TABLE `Trailer` DISABLE KEYS */;
INSERT INTO `Trailer` VALUES (1,'TomorrowHome','Jeanne','00:01:20','','2020-02-06 06:00:00','','','This is a working Example of Editing a trailer.Is this Working?','2020-02-20 06:00:00','2020-02-27 06:00:00',1),(37,'Moon Landing','Mary','00:30:03','','2020-02-27 06:00:00','','','Test add Trailer.Mote Test Over and Over','2020-02-27 06:00:00','2020-02-27 06:00:00',1),(38,'Once in blue moon','Mary','00:02:30','','2020-03-06 06:00:00','','','This is a new Trailer before implementing Upload covers and videos','2020-03-06 06:00:00','2020-03-06 06:00:00',1);
/*!40000 ALTER TABLE `Trailer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `faq_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `category` varchar(200) DEFAULT NULL,
  `description` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  PRIMARY KEY (`faq_id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `faq_User_user_id_fk` (`user`),
  CONSTRAINT `faq_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'How movies are created.','Movie','This is FAQ Creates Test. More and more. Very Happy it works!!!yes','2020-02-28 06:00:00','2020-03-05 14:26:50',2);
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-09 15:24:04
