
DROP TABLE IF EXISTS `Crew`;
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
);

--
-- Table structure for table `Film`
--

DROP TABLE IF EXISTS `Film`;

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
) ;

--
-- Dumping data for table `Film`
--

DROP TABLE IF EXISTS `FilmCrew`;

CREATE TABLE `FilmCrew` (
                            `film_crew_id` int NOT NULL AUTO_INCREMENT,
                            `created_at` timestamp NOT NULL,
                            `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `film_id` int NOT NULL,
                            `crew_id` int NOT NULL,
                            PRIMARY KEY (`film_crew_id`),
                            KEY `FilmCrew_Crew_fk` (`crew_id`),
                            KEY `FilmCrew_Film_fk` (`film_id`),
                            CONSTRAINT `FilmCrew_Crew_fk` FOREIGN KEY (`crew_id`) REFERENCES `Crew` (`crew_id`),
                            CONSTRAINT `FilmCrew_Film_fk` FOREIGN KEY (`film_id`) REFERENCES `Film` (`film_id`)
) ;
--
-- Table structure for table `FilmGenre`
--

DROP TABLE IF EXISTS `FilmGenre`;

CREATE TABLE `FilmGenre` (
                             `film_genre_id` int NOT NULL AUTO_INCREMENT,
                             `created_at` timestamp NOT NULL,
                             `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `genre_id` int NOT NULL,
                             `film_id` int NOT NULL,
                             PRIMARY KEY (`film_genre_id`),
                             KEY `FilmGenre_Film` (`film_id`),
                             KEY `FilmGenre_Genre` (`genre_id`),
                             CONSTRAINT `FilmGenre_Film` FOREIGN KEY (`film_id`) REFERENCES `Film` (`film_id`),
                             CONSTRAINT `FilmGenre_Genre` FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`)
) ;

--
-- Table structure for table `Genre`
--

DROP TABLE IF EXISTS `Genre`;
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

--

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role` (
                        `role_id` int NOT NULL AUTO_INCREMENT,
                        `name` char(30) NOT NULL,
                        `created_at` timestamp NOT NULL,
                        `username` varchar(120) NOT NULL,
                        `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`role_id`),
                        KEY `Role_User_username_fk` (`username`),
                        CONSTRAINT `Role_User_username_fk` FOREIGN KEY (`username`) REFERENCES `SM_Users` (`username`)
) ;

--

DROP TABLE IF EXISTS `SM_Users`;

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
) ;

--
-- Table structure for table `Trailer`
--

DROP TABLE IF EXISTS `Trailer`;
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
);
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;

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
) ;