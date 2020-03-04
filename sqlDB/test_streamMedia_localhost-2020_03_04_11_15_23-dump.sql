-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: test_streamMedia
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Crew`
--

LOCK TABLES `Crew` WRITE;
/*!40000 ALTER TABLE `Crew` DISABLE KEYS */;
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
  `cover` varchar(200) NOT NULL,
  `created_at` int NOT NULL,
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
  `film_crew_id` int NOT NULL AUTO_INCREMENT,
  `film_id` int NOT NULL,
  `crew_id` int NOT NULL,
  PRIMARY KEY (`film_crew_id`),
  KEY `FilmCrew_Crew_fk` (`crew_id`),
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
  `film_genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_id` int NOT NULL,
  `film_id` int NOT NULL,
  PRIMARY KEY (`film_genre_id`),
  KEY `FilmGenre_Film` (`film_id`),
  KEY `FilmGenre_Genre` (`genre_id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'user','2020-02-29 03:58:16','jcoyne','2020-02-29 03:58:16'),(2,'admin','2020-02-29 03:58:16','fhensen','2020-02-29 03:58:16'),(3,'developer','2020-02-29 03:58:16','bcurry','2020-02-29 03:58:16'),(4,'sale','2020-02-29 03:58:16','kmack','2020-02-29 03:58:16'),(5,'staff','2020-02-29 03:58:16','bcurry','2020-02-29 03:58:16');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SM_Users`
--

LOCK TABLES `SM_Users` WRITE;
/*!40000 ALTER TABLE `SM_Users` DISABLE KEYS */;
INSERT INTO `SM_Users` VALUES (1,'jcoyne','jcoyne@streammedia.com','supersecret1','Joe','Coyne','1964-04-01',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16'),(2,'fhensen','fhensen@streammedia.com','supersecret2','Fred','Hensen','1988-05-08',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16'),(3,'bcurry','bcurry@streammedia.com','supersecret3','Barney','Curry','1947-11-11',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16'),(4,'kmack','kmack@streammedia.com','supersecret4','Karen','Mack','1986-07-08',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16'),(5,'dklein','dklein@streammedia.com','supersecret5','Dianne','Klein','1991-09-22',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16'),(6,'dtillman','dtillman@streammedia.com','supersecret6','Dawn','Tillman','1979-08-30',NULL,NULL,NULL,'2020-02-29 03:58:16','2020-02-29 03:58:16');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trailer`
--

LOCK TABLES `Trailer` WRITE;
/*!40000 ALTER TABLE `Trailer` DISABLE KEYS */;
INSERT INTO `Trailer` VALUES (1,'Home Sweet Home','James','00:01:40',NULL,'2020-02-29 03:58:16',NULL,NULL,'Hibernate ORM is concerned with helping your application to achieve persistence.','2020-02-29 03:58:16','2020-02-29 03:58:16',1),(2,'Hope','Henry','00:01:40',NULL,'2020-02-29 03:58:16',NULL,NULL,'So what is persistence? Persistence simply means that we would like our application’s data to outlive the applications process.','2020-02-29 03:58:16','2020-02-29 03:58:16',6),(3,'Peace','Chantal','00:01:40',NULL,'2020-02-29 03:58:16',NULL,NULL,' In Java terms, we would like the state of (some of) our objects to live beyond the scope of the JVM so that the same state is available later.','2020-02-29 03:58:16','2020-02-29 03:58:16',2),(4,'The happiness','Nadine','00:01:40',NULL,'2020-02-29 03:58:16',NULL,NULL,'So what is persistence?','2020-02-29 03:58:16','2020-02-29 03:58:16',2),(5,'Great Dane','Ella','00:01:40',NULL,'2020-02-29 03:58:16',NULL,NULL,'Hibernate ORM is concerned with helping your application to achieve persistence.','2020-02-29 03:58:16','2020-02-29 03:58:16',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'How to sell movies','Movie','This is a description of how to sell movies.','2020-02-29 03:58:16','2020-02-29 03:58:16',1),(2,'How to access paid materials ','Sotories','Paid Materials are only accessible to users who have an account on this web site or pay for them where they are hosted.','2020-02-29 03:58:16','2020-02-29 03:58:16',2),(3,'Taking better photos','Movie',' In Java terms, we would like the state of (some of) our objects to live beyond the scope of the JVM so that the same state is available later.','2020-02-29 03:58:16','2020-02-29 03:58:16',2),(7,'Test','Trailer','Sme Data for Test','2020-02-29 03:58:17','2020-02-29 03:58:17',2);
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

-- Dump completed on 2020-03-04 11:15:23
