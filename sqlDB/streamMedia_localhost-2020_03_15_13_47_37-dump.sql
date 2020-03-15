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
-- Table structure for table `BKCategory`
--

DROP TABLE IF EXISTS `BKCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BKCategory` (
  `bkCategory_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`bkCategory_id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BKCategory`
--

LOCK TABLES `BKCategory` WRITE;
/*!40000 ALTER TABLE `BKCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `BKCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `isbn` char(25) NOT NULL,
  `author` varchar(200) NOT NULL,
  `pub_date` timestamp NULL DEFAULT NULL,
  `edition` char(20) DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `publisher` varchar(150) DEFAULT NULL,
  `page_number` int DEFAULT NULL,
  `summary` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  UNIQUE KEY `title_isbn_UNIQUE` (`title`,`isbn`),
  KEY `Book_SM_Users` (`user_id`),
  CONSTRAINT `Book_SM_Users` FOREIGN KEY (`user_id`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookCategory`
--

DROP TABLE IF EXISTS `BookCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BookCategory` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `bkCategory_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`bkCategory_id`),
  UNIQUE KEY `bkCategory_book_UNIQUE` (`bkCategory_id`,`book_id`),
  CONSTRAINT `BookCategory_BKCategory` FOREIGN KEY (`bkCategory_id`) REFERENCES `BKCategory` (`bkCategory_id`),
  CONSTRAINT `BookCategory_Book` FOREIGN KEY (`book_id`) REFERENCES `Book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookCategory`
--

LOCK TABLES `BookCategory` WRITE;
/*!40000 ALTER TABLE `BookCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `BookCategory` ENABLE KEYS */;
UNLOCK TABLES;

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
  UNIQUE KEY `title_artist_UNIQUE` (`email`),
  KEY `Crew_User_user_id_fk` (`user`),
  CONSTRAINT `Crew_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Crew`
--

LOCK TABLES `Crew` WRITE;
/*!40000 ALTER TABLE `Crew` DISABLE KEYS */;
INSERT INTO `Crew` VALUES (1,'Lisa','Larson','llarson@example.com','Sales','This is sales.','2020-03-15 05:00:00','2020-03-15 05:00:00',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
INSERT INTO `Genre` VALUES (1,'Romance','Stages of \'falling in love\' and the subsequent break-up and reconciliation, forbidden love, true love, fairy tales.','2020-03-14 05:00:00','2020-03-14 05:00:00');
/*!40000 ALTER TABLE `Genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Music`
--

DROP TABLE IF EXISTS `Music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Music` (
  `music_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(120) NOT NULL,
  `music_video` varchar(250) NOT NULL,
  `music_cover` varchar(250) DEFAULT NULL,
  `artist` varchar(80) DEFAULT NULL,
  `duration` time NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`music_id`),
  UNIQUE KEY `title_artist_UNIQUE` (`title`,`artist`),
  KEY `Music_SM_Users` (`user_id`),
  CONSTRAINT `Music_SM_Users` FOREIGN KEY (`user_id`) REFERENCES `SM_Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Music`
--

LOCK TABLES `Music` WRITE;
/*!40000 ALTER TABLE `Music` DISABLE KEYS */;
/*!40000 ALTER TABLE `Music` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'admin','2020-03-10 05:00:00','admin','2020-03-14 05:00:00'),(2,'user','2020-03-11 05:00:00','mary','2020-03-14 05:00:00'),(3,'user','2020-03-12 05:00:00','ian','2020-03-14 05:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SM_Users`
--

LOCK TABLES `SM_Users` WRITE;
/*!40000 ALTER TABLE `SM_Users` DISABLE KEYS */;
INSERT INTO `SM_Users` VALUES (1,'admin','admin@streammedia.com','466881cdec1517cb3f64f996221c406f4052cc8a2f7f1b55f4ff384cba64746b$1$7f8e02e89785d422ea2d90d30722781003ccc4eb0ba7e44bfdf7cf76f195cd78','Jeanne','Uwimana','1999-04-13','Other','','Biography','2020-03-10 05:00:00','2020-03-14 05:00:00'),(2,'mary','mary@streammedia.com','55358220852e9d54551cfdcf4a042b603373f92637884ac737623554aa2855f4$1$200385ea76e66dd711740d26930a7898c82902c7cc9bdc6b32c66ae56eba4b1f','Mary','Anne','2003-02-10','Female','','Biography More.','2020-03-11 05:00:00','2020-03-14 05:00:00'),(3,'ian','ian@madisoncollege.edu','c719840bc92e023d8d62531ab305d69dd545f7a255541cd8173ba9d59cd9b41c$1$bb9c048130cd46f2288ae27399c5bb241dc5b7bb2a6a30250ce85688d5ee658d','Ian','Torrence','2020-03-14','Male','','Some Bio.','2020-03-12 05:00:00','2020-03-14 05:00:00');
/*!40000 ALTER TABLE `SM_Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShortStory`
--

DROP TABLE IF EXISTS `ShortStory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ShortStory` (
  `short_story_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author` varchar(200) NOT NULL,
  `cover` varchar(250) DEFAULT NULL,
  `publication_date` timestamp NOT NULL,
  `description` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`short_story_id`),
  UNIQUE KEY `title_author_UNIQUE` (`title`,`author`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShortStory`
--

LOCK TABLES `ShortStory` WRITE;
/*!40000 ALTER TABLE `ShortStory` DISABLE KEYS */;
/*!40000 ALTER TABLE `ShortStory` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trailer`
--

LOCK TABLES `Trailer` WRITE;
/*!40000 ALTER TABLE `Trailer` DISABLE KEYS */;
INSERT INTO `Trailer` VALUES (1,'Hope of Tomorrow','Jeanne','01:01:21','','2020-03-13 19:40:41','','','Some summary to test add trailer.This Work!','2020-03-14 19:01:41','2020-03-14 19:40:37',1),(2,'Tomorrow\'s Home','Mary','01:01:30','','2020-03-14 19:45:05','','','Some Data. More','2020-03-14 19:45:05','2020-03-14 19:46:10',1);
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
INSERT INTO `faq` VALUES (1,'What is your best movie made?','Movies','The best movie made is what is life?','2020-03-11 22:20:57','2020-03-11 22:21:05',1),(3,'Where to find new trailers','Trailers','The Trailers are located under media drop-down menu.New trailers will be at the top.','2020-03-14 16:50:02','2020-03-14 16:50:02',1);
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

-- Dump completed on 2020-03-15 13:47:39
