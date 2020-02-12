-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-02-07 17:10:33.785

-- tables
-- Table: Role
CREATE TABLE Role (
                      role_id int NOT NULL AUTO_INCREMENT,
                      name char(30) NOT NULL,
                      created_at timestamp NOT NULL,
                      updated_at timestamp NOT NULL,
                      username varchar(60) NOT NULL,
                      CONSTRAINT Role_pk PRIMARY KEY (role_id)
);

-- Table: SM_Users
DROP TABLE IF EXISTS `SM_Users`;
CREATE TABLE SM_Users (
                          user_id int NOT NULL AUTO_INCREMENT,
                          username varchar(60) NOT NULL,
                          email varchar(120) NOT NULL,
                          password varchar(200) NOT NULL,
                          first_name varchar(80) NULL,
                          last_name varchar(90) NULL,
                          birthdate date NULL,
                          gender char(15) NULL,
                          picture blob NULL,
                          biography text NULL,
                          created_at timestamp NOT NULL,
                          updated_at timestamp NOT NULL,
                          CONSTRAINT SM_Users_pk PRIMARY KEY (user_id),
                          UNIQUE KEY `username_UNIQUE` (`username`)
);

UPDATE SM_Users
SET
    email = 'kjames@madisoncollege.edu',
    username = 'kjames',
    first_name = 'King',
    last_name = 'James',
    password = 12345
WHERE
        user_id = 4;
-- End of file.
