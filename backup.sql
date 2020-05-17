-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: streamMedia
-- ------------------------------------------------------
-- Server version	8.0.20

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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BKCategory`
--

LOCK TABLES `BKCategory` WRITE;
/*!40000 ALTER TABLE `BKCategory` DISABLE KEYS */;
INSERT INTO `BKCategory` VALUES (1,'Science Fiction','Though they\'re often thought of in the same vein as fantasy, what distinguishes science fiction stories is that they lean heavily on themes of technology and future science. You\'ll find apocalyptic and dystopian novels in the sci-fi genre as well.','2020-04-04 16:32:22','2020-04-29 00:11:17'),(2,'Biographies and Autobiographies','Serving as an official account of the details and events of a person\'s life span, autobiographies are written by the subject themselves, while biographies are written by an author who is not the focus of the book.','2020-04-04 16:47:26','2020-04-29 00:13:44'),(5,'Test Book Category','This is a test book entry.','2020-05-01 14:35:56','2020-05-01 14:35:56');
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (4,'The Violet and the Tom','288126522742','Eve Ocotillo ','2020-04-25 14:20:55','','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/the_violet_and_the_tom_Eve_Ocotillo.jpg','FictionPress.com',299,'In what might have been the middle ages, had neither Alexander the Great nor Jesus the prophet died young, the Greek State is a powerful economic force in southern Europe, and slavery is a profitable and well-entrenched social institution.','2020-04-25 14:20:55','2020-05-10 05:10:33',1),(5,'The moon','4242424242424','Mary','2020-04-29 00:13:44','','','Laurent',345,'This is a test.','2020-04-29 00:13:44','2020-04-29 00:13:44',1),(7,'Test Book','288126522462','Jeanne','2020-05-01 15:03:44','1','','Laurent',546,'Test Book Entry.','2020-05-01 15:03:44','2020-05-01 15:03:44',1),(8,'Test Add book','5242424314142','Noella','2020-05-07 21:10:10','','','FictionPress.com',4352,'Test for adding a new book.','2020-05-07 21:10:10','2020-05-07 21:10:38',1),(9,'Book Cover to S3','5353442424241','Nadia Yvonne','2020-05-10 04:43:25','','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/book_cover_to_s3_Nadia_Yvonne.jpg','Diane Neblique',675,'Test Upload book to S3 with unsupported Extension.','2020-05-10 04:43:25','2020-05-10 05:11:52',1),(10,'Book S3','65655666363','Angelique','2020-05-10 04:51:55','','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/book_s3_Angelique.jpg','Nadine',786,'Test Upload Cover to S3.','2020-05-10 04:51:59','2020-05-10 04:51:59',1);
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookCategory`
--

LOCK TABLES `BookCategory` WRITE;
/*!40000 ALTER TABLE `BookCategory` DISABLE KEYS */;
INSERT INTO `BookCategory` VALUES (4,1),(5,2),(7,1),(7,2),(7,5),(8,2),(8,5),(9,5),(10,2),(10,5);
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Crew`
--

LOCK TABLES `Crew` WRITE;
/*!40000 ALTER TABLE `Crew` DISABLE KEYS */;
INSERT INTO `Crew` VALUES (1,'Lisa','Larson','llarson@example.com','Sales','This is sales.More data','2020-03-15 05:00:00','2020-05-12 05:00:00',1),(2,'Gustavo','Diaz','gdiaz@example.com','Film Maker','I love create media content.','2020-03-27 05:00:00','2020-03-27 05:00:00',1),(3,'Emma','Mary','emary@streammedia.com','Teacher','Test Add Crew','2020-05-05 05:00:00','2020-05-05 05:00:00',1);
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
  `link` varchar(180) DEFAULT NULL,
  `video` varchar(200) DEFAULT NULL,
  `cover` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `summary` text NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  `episode` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`film_id`),
  UNIQUE KEY `title_UNIQUE` (`title`,`director`),
  KEY `Film_User_user_id_fk` (`user`),
  CONSTRAINT `Film_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Film`
--

LOCK TABLES `Film` WRITE;
/*!40000 ALTER TABLE `Film` DISABLE KEYS */;
INSERT INTO `Film` VALUES (1,'Hope of Tomorrow','02:23:34','James Doe','2020-03-27 17:44:38',NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/film/videos/hope_of_tomorrow_James_Doe.mp4','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/film/covers/hope_of_tomorrow_James_Doe.jpg','2020-03-27 05:00:00','Some data and Upload Video and cover while editing.','2020-05-10 05:00:00',1,''),(2,'Test films','01:00:05','Ian Declan','2020-05-01 16:02:02',NULL,'','','2020-05-01 05:00:00','Test film more.','2020-05-05 05:00:00',1,''),(3,'Test film 2','02:45:09','','2020-05-01 16:03:19',NULL,'','','2020-05-01 05:00:00','Test 2 film.','2020-05-05 05:00:00',1,''),(4,'Test Message','03:00:01','Emma Inema','2020-05-05 16:53:32',NULL,'','','2020-05-05 05:00:00','Test Messages.','2020-05-05 05:00:00',1,''),(5,'Test success Message','03:00:01','Ian Rogers','2020-05-05 16:57:39',NULL,'','','2020-05-05 05:00:00','Test Messages.','2020-05-05 05:00:00',1,''),(6,'Film with cover','03:00:03','Eric Jones','2020-05-10 05:40:23',NULL,NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/film_with_cover_Eric_Jones.jpg','2020-05-10 05:00:00','Test Film With cover.','2020-05-10 05:00:00',1,''),(7,'Film with Cover and Video','02:35:09','Declan Rogers','2020-05-10 05:53:36',NULL,NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/videos/film_with_cover_and_video_Declan_Rogers.mp4','2020-05-10 05:00:00','Test add film Video and cover.','2020-05-10 05:00:00',1,''),(8,'Film With Video','02:23:02','Ellia Newman','2020-05-10 06:15:06',NULL,NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/book/videos/film_with_video_Ellia_Newman.mp4','2020-05-10 05:00:00','Test Upload a video.','2020-05-10 05:00:00',10,''),(9,'Upload Video to S3','02:23:02','Melanie','2020-05-10 12:49:42',NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/film/videos/upload_video_to_s3_Melanie.mp4','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/film/covers/upload_video_to_s3_Melanie.jpg','2020-05-10 05:00:00','Test Upload Video to S3.','2020-05-10 05:00:00',1,''),(10,'Upload Video and cover to S3','02:23:02','Melanie','2020-05-10 12:51:43',NULL,'https://ju-stream-media.s3.us-east-2.amazonaws.com/media/film/videos/upload_video_and_cover_to_s3_Melanie.mp4',NULL,'2020-05-10 05:00:00','Test Upload Video and cover of a film to S3.','2020-05-10 05:00:00',1,'');
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilmCrew`
--

LOCK TABLES `FilmCrew` WRITE;
/*!40000 ALTER TABLE `FilmCrew` DISABLE KEYS */;
INSERT INTO `FilmCrew` VALUES (1,1),(1,2),(2,2),(3,1),(3,2),(4,2),(4,3),(5,3),(6,1),(7,1),(7,2),(7,3),(8,2),(9,1),(9,3),(10,1),(10,3);
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilmGenre`
--

LOCK TABLES `FilmGenre` WRITE;
/*!40000 ALTER TABLE `FilmGenre` DISABLE KEYS */;
INSERT INTO `FilmGenre` VALUES (1,1),(2,2),(3,2),(2,3),(4,3),(2,4),(3,4),(2,5),(3,5),(2,6),(3,6),(12,7),(13,7),(21,7),(3,8),(3,9),(3,10);
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
) ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
INSERT INTO `Genre` VALUES (1,'Romance','Stages of \'falling in love\' and the subsequent break-up and reconciliation, forbidden love, true love, fairy tales.','2020-03-14 05:00:00','2020-05-10 05:00:00'),(2,'Horror','Scary movies.','2020-05-01 05:00:00','2020-05-10 05:00:00'),(3,'Adventure','Explore and have fun.','2020-05-01 05:00:00','2020-05-10 05:00:00'),(4,'Documentary','Research a topic and make a movie of the event.','2020-05-01 05:00:00','2020-05-05 05:00:00'),(6,'Test1','Test Add Genre','2020-05-03 05:00:00','2020-05-03 05:00:00'),(12,'Test 2','Test Genre Item.','2020-05-03 05:00:00','2020-05-10 05:00:00'),(13,'Test 3','Test Genre Item.','2020-05-03 05:00:00','2020-05-10 05:00:00'),(21,'Test 7','Test Genre Item.','2020-05-04 05:00:00','2020-05-10 05:00:00'),(22,'Test 8','Some Test Data.','2020-05-04 05:00:00','2020-05-04 05:00:00'),(23,'Test 9','Some Test Data.','2020-05-04 05:00:00','2020-05-04 05:00:00'),(24,'Test 10','Some Test Data.','2020-05-04 05:00:00','2020-05-04 05:00:00');
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
);
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'admin','2020-03-10 05:00:00','admin','2020-05-12 05:00:00'),(2,'user','2020-03-11 05:00:00','mary','2020-05-07 05:00:00'),(3,'user','2020-03-12 05:00:00','ian','2020-05-05 05:00:00'),(4,'user','2020-04-16 05:00:00','larson','2020-04-16 05:00:00'),(5,'user','2020-04-26 05:00:00','elisa','2020-04-26 05:00:00'),(7,'user','2020-05-06 05:00:00','joy','2020-05-06 05:00:00'),(8,'user','2020-05-07 05:00:00','newman','2020-05-07 05:00:00'),(9,'admin','2020-05-07 05:00:00','jeanne','2020-05-09 05:00:00'),(10,' media creator','2020-05-07 05:00:00','ernest','2020-05-08 05:00:00'),(11,'user','2020-05-07 05:00:00','blandine','2020-05-07 05:00:00'),(12,'user','2020-05-11 05:00:00','denyse','2020-05-11 05:00:00'),(13,'user','2020-05-11 05:00:00','edenyse','2020-05-11 05:00:00'),(14,'user','2020-05-12 05:00:00','test','2020-05-12 05:00:00'),(15,'media creator','2020-05-12 05:00:00','test2','2020-05-12 05:00:00');
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SM_Users`
--

LOCK TABLES `SM_Users` WRITE;
/*!40000 ALTER TABLE `SM_Users` DISABLE KEYS */;
INSERT INTO `SM_Users` VALUES (1,'admin','admin@streammedia.com','466881cdec1517cb3f64f996221c406f4052cc8a2f7f1b55f4ff384cba64746b$1$7f8e02e89785d422ea2d90d30722781003ccc4eb0ba7e44bfdf7cf76f195cd78','Admin','Developer','1998-04-11','Female','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/admin/userProfile.png','Biography of admin.','2020-03-10 05:00:00','2020-05-12 05:00:00'),(2,'mary','mary@streammedia.com','55358220852e9d54551cfdcf4a042b603373f92637884ac737623554aa2855f4$1$200385ea76e66dd711740d26930a7898c82902c7cc9bdc6b32c66ae56eba4b1f','Mary','Anne','2000-02-04','Female','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/mary/userProfile.png','Biography More.Test','2020-03-11 05:00:00','2020-05-07 05:00:00'),(3,'ian','ian@madisoncollege.edu','c719840bc92e023d8d62531ab305d69dd545f7a255541cd8173ba9d59cd9b41c$1$bb9c048130cd46f2288ae27399c5bb241dc5b7bb2a6a30250ce85688d5ee658d','Ian','Torrence','2002-02-03','Male','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/ian/userProfile.png','Some Bio for Ian.','2020-03-12 05:00:00','2020-05-05 05:00:00'),(4,'larson','llarson@example.com','b55e3d045e6a9128fff2b8daffc988984eab13033c4e5904f64708cbd13cf75f$1$bbcf109a364001fc66713c4669e772be271fd835a66becca2a3513b903f2d676','Lisa','Larson',NULL,NULL,NULL,NULL,'2020-04-16 05:00:00','2020-04-16 05:00:00'),(5,'elisa','elisa@example.com','946d297d2da115d48ec22498e877085ea93273df6e1370b25f6f95c811d5d510$1$05b5eafa4b5eb2da707996d1cb76bade607509f5bd8ccf9fd5965ccfe458b719','Elisa ','James',NULL,NULL,NULL,NULL,'2020-04-26 05:00:00','2020-04-26 05:00:00'),(8,'joy','njoy@streammedia.com','b100a9addbd0b367a65f27f8943a8953352fe24ae20828cc656271b35e28d670$1$e1473a1603898fc58dacc4ee6a2dc1410cfe3bb7f1c0cd021ad2592b5cbc0b02','Nadia','Joy',NULL,NULL,NULL,NULL,'2020-05-06 05:00:00','2020-05-06 05:00:00'),(9,'newman','anewman@streammedia.com','494f487003926c76d5847d95c23c0d50baa7e8700b1d6c3fa53168c7b629bdca$1$5d0884e81cdb00aa77af889867b3bd1e99f4d7810996eb21cb55933afa77ebc0','Adeline','Newman','1995-05-06','Female','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/newman/userProfile.png','Bio for Adeline','2020-05-07 05:00:00','2020-05-07 05:00:00'),(10,'jeanne','ujeanne@streammedia.com','a3317046e4ea52e55d0bb277aa908f05d16ec5df18b4526e2e671e9512dc9998$1$0b09153c36aae9e0953ffd1251cc91228a9aa49fab3817080e0ca596ab5803fe','Jeanne','Uwimana','1979-09-05','Female','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/jeanne/userProfile.png','Jeanne\'s bio.','2020-05-07 05:00:00','2020-05-09 05:00:00'),(11,'ernest','ehope@streammedia.com','f15fc381ec5cc40336b15c772119c9ee1cda1c3924b2045a1b90f5ecbe025c0e$1$26ad1ed631bb9c4e33e550f580fd91919afe336921ec315bdeb1a52086d0cfd9','Ernest','Hope','1982-11-01','Male','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/ernest/userProfile.png','Ernest\'s Bio','2020-05-07 05:00:00','2020-05-08 05:00:00'),(12,'blandine','iblandine@streammedia.com','70f7277c9cc9af858b4b9d9606fb3d9bcde7c93eb99f151b81aca03fe1315780$1$b3ddfbab0a364152172cdc5a8a30a4d6af6ea636e4d0a5faf1903d374a1920ca','Blandine','James',NULL,NULL,NULL,NULL,'2020-05-07 05:00:00','2020-05-07 05:00:00'),(13,'denyse','edenyse@media.com','38f422dd0a16c88df6447ebe10caabd3d51ac29330597258746e92a59f2399d2$1$55fb556af45a2382a883eb8c3a765049dc2a73d8617a0e02e13dffb24208d1df','Denyse','Ineza','1989-02-09','Other','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/denyse/userProfile.png','Denyse Bio.','2020-05-11 05:00:00','2020-05-11 05:00:00'),(14,'edenyse','denyse@media.com','de32fcc91674f18b7ac1704f03f526972d1d5f9c15989eaecc1838da3cbd2942$1$fb1330c5431eb6f354016248eef7876e778c69e63352c0539b7490ea07e7030b','Denyse','Mignone','2020-05-05','Female',NULL,'Denyse\'s bio.','2020-05-11 05:00:00','2020-05-11 05:00:00'),(15,'test','test@streammedia.com','c68b83cb548b219c7fb1fecd6ab502839b7b2192b8deae5cc3ff25fcb152c45e$1$7c0e4f958dca758e021c79a103a1e4a9c11b207af44e959457a0c21f8bf11026','Test','One','1992-02-10','Female','https://ju-stream-media.s3.us-east-2.amazonaws.com/images/test/userProfile.png','Test one bio.','2020-05-12 05:00:00','2020-05-12 05:00:00'),(16,'test2','test2@streammedia.com','8295eb6d0d9780f0b839f8e878bdb3fc84672fd2d7b239bcd7b0936ff8e017a8$1$feb186be89a7b42ee27545ca9ef88460a2c4365749e2f63e8e877b06d538df30','Test2','Two',NULL,NULL,NULL,NULL,'2020-05-12 05:00:00','2020-05-12 05:00:00');
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShortStory`
--

LOCK TABLES `ShortStory` WRITE;
/*!40000 ALTER TABLE `ShortStory` DISABLE KEYS */;
INSERT INTO `ShortStory` VALUES (1,'The little town','Jeanne','media/story.jpg','2020-04-24 14:20:28','This is the little town story.','2020-04-24 14:20:28','2020-05-10 00:28:16',1),(2,'A good day to go outside.','Noela','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/shortstory/a_good_day_to_go_outside._Noela.jpg','2020-04-24 14:51:16','This is a special day since we are all okay.','2020-04-24 14:51:16','2020-05-12 14:28:00',1),(4,'Test to upload cover to S3','Noela','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/shortstory/test_to_upload_cover_to_s3_Noela.jpg','2020-05-09 15:20:01','Short Story with upload.','2020-05-09 15:20:01','2020-05-10 00:25:49',1),(6,'Test Upload S3','Jane','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/shortstory/test_upload_s3_Jane.jpg','2020-05-09 16:25:58','Test upload to S3 is working','2020-05-09 16:27:28','2020-05-09 16:27:28',10),(7,'Test Story','Jeanne','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/shortstory/test_story_Jeanne.jpg','2020-05-12 16:30:52','Some Text.','2020-05-12 16:30:54','2020-05-12 16:30:54',1),(8,'Java Story 12','Jeanne','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/shortstory/java_story_Jeanne.jpg','2020-05-12 16:43:27','Summary','2020-05-12 16:43:28','2020-05-12 16:43:45',1);
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
  `link` varchar(500) DEFAULT NULL,
  `video` varchar(300) DEFAULT NULL,
  `summary` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user` int NOT NULL,
  PRIMARY KEY (`trailer_id`),
  UNIQUE KEY `title_UNIQUE` (`title`,`author`),
  KEY `tariler_User_user_id_fk` (`user`),
  CONSTRAINT `tariler_User_user_id_fk` FOREIGN KEY (`user`) REFERENCES `SM_Users` (`user_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trailer`
--

LOCK TABLES `Trailer` WRITE;
/*!40000 ALTER TABLE `Trailer` DISABLE KEYS */;
INSERT INTO `Trailer` VALUES (1,'Hope of Tomorrow','Mary','01:01:21','media/trailer/covers/trailerCover_1.gif','2020-03-13 19:40:41','','media/trailer/videos/trailerVideo_1.mp4','Some summary to test add trailer.This Work!Update Test','2020-03-14 19:01:41','2020-04-20 22:12:45',1),(3,'Explore the world','Noela','01:00:04','media/trailer1.jpg','2020-04-16 20:23:00','','/media/trailer/videos/trailerv1.mp4','Summary','2020-04-17 13:43:35','2020-04-22 01:44:05',1),(4,'Moon Landing','Jeanne','01:00:12','media/trailer1.jpg','2020-04-17 15:07:17','','media/trailer/videos/trailerVideo_4.mp4','The moon landing trailer.','2020-04-17 15:07:18','2020-04-23 21:26:57',1),(5,'The Moon Landing','Jeanne Mary','01:00:12','media/trailer/covers/trailer1.jpg','2020-04-17 15:15:07','','media/trailerv.mp4','Test more Test','2020-04-17 15:15:07','2020-04-29 00:10:37',1),(6,'One upon time','Jeanne','01:00:12','media/trailer1.jpg','2020-04-17 16:10:57','','media/trailerv.mp4','Summary','2020-04-17 16:10:57','2020-04-22 01:44:06',1),(7,'One upon time','Mary','01:00:12','media/trailer/covers/trailer1.jpg','2020-04-17 16:23:02','','media/trailerv.mp4','Summary Test.','2020-04-17 16:23:02','2020-04-17 17:49:49',1),(8,'Once in blue moon','Mary','01:00:12','media/trailer/covers/trailerCover_8.jpg','2020-04-17 16:33:14','','media/trailerv.mp4','Summary Test.','2020-04-17 16:33:14','2020-04-22 02:03:46',1),(10,'Happy day','Jeanne','01:00:05','media/trailer/covers/3x0ns9.gif','2020-04-17 17:44:46','','media/trailerv.mp4','Some Data','2020-04-17 17:44:46','2020-04-17 17:49:49',1),(12,'One upon time in USA','Mary','01:00:05','media/trailer/covers/avataaars5.png','2020-04-17 22:43:12','',NULL,'Summary','2020-04-17 22:43:12','2020-04-17 22:43:12',1),(14,'Cover test','James','01:01:21','media/trailer/covers/avataaars4.png','2020-04-17 23:11:54','',NULL,'Test','2020-04-17 23:11:55','2020-04-17 23:11:55',1),(15,'Hope of Tomorrow again','Jeanne','01:01:21','media/trailer1.jpg','2020-04-17 23:45:29','',NULL,'Test and Test','2020-04-17 23:45:29','2020-04-22 01:44:06',1),(16,'Happy again','Jeanne','01:01:21','media/trailer/covers/avataaars5.png','2020-04-17 23:46:16','',NULL,'Test and Test','2020-04-17 23:48:26','2020-04-17 23:48:26',1),(18,'Test Upload video','Jeanne','01:00:05','media/trailer/covers/avataaars.png','2020-04-10 19:03:00','','media/trailer/videos/190111_15_Misc_UHD_01.mp4','Some data.','2020-04-19 03:28:14','2020-04-19 03:28:14',1),(19,'Test Upload another video','Jeanne','01:00:05','media/trailer/covers/trailerCover_19.jpg','2020-04-10 19:03:00','','media/trailer/videos/trailerVideo_19.mp4','Some data.','2020-04-19 03:29:28','2020-04-19 18:13:27',1),(20,'Test Upload cover and Video','Jeanne','01:00:05','media/trailer/covers/trailer1.jpg','2020-04-10 19:03:00','','media/trailer/videos/production ID_4047768 (1).mp4','Some data.','2020-04-19 04:14:27','2020-04-19 04:14:27',1),(21,'Upload and resname','Jeanne','01:00:05','media/trailer1.jpg','2020-04-10 19:03:00','','media/trailer/videos/trailerv.mp4','Test upload video and image.','2020-04-19 22:27:39','2020-04-22 01:44:06',1),(26,'Upload and save','Jeanne','01:00:05','media/trailer1.jpg','2020-04-10 19:03:00','','media/trailer/videos/trailerv.mp4','Test upload video and image.','2020-04-19 22:35:49','2020-04-22 01:44:06',1),(27,'Upload and Copy File directory','Noela','01:00:05','media/trailer/covers/avataaars5.png','2020-04-10 19:03:00','','media/trailer/videos/trailerv.mp4','Test upload video and image.','2020-04-19 22:41:51','2020-04-19 22:41:51',1),(28,'New Trailer','Jeanne','01:00:05','media/trailer/covers/trailerc.jpg','2020-04-21 23:29:46','','media/trailer/videos/190111_15_Misc_UHD_01 (1).mp4','New Trailer.','2020-04-21 23:29:49','2020-04-21 23:29:49',1),(32,'Trailer S3 c','Nadia Yvonne','01:00:12',NULL,'2020-05-10 16:04:28','','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/trailer/videos/trailer_s3_c_Nadia_Yvonne.mp4','Test Upload unsupported image extension.','2020-05-10 16:04:43','2020-05-10 16:04:43',10),(34,'test Trailer Java','Jeanne','02:23:02','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/trailer/covers/test_trailer_Jeanne.jpg','2020-05-12 16:41:24','','https://ju-stream-media.s3.us-east-2.amazonaws.com/media/trailer/videos/test_trailer_Jeanne.mp4','Summary Test','2020-05-12 16:41:55','2020-05-12 16:42:20',1);
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
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'What is the best movie made?','Movies','The best movie made is what is life?More to add this is the update.','2020-03-11 22:20:57','2020-05-09 15:14:57',1),(5,'The interesting movie','Movies','It all depends on audience\'s interest.','2020-04-28 19:27:34','2020-04-28 19:27:48',1),(7,'Last Test','Test','This is a test for adding and edit FAQ.','2020-05-07 20:42:28','2020-05-07 20:48:36',1);
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

-- Dump completed on 2020-05-16 22:30:20
