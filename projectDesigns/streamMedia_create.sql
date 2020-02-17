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
CREATE TABLE SM_Users (
                          user_id int NOT NULL AUTO_INCREMENT,
                          username varchar(60) NOT NULL,
                          email varchar(120) NOT NULL,
                          password varchar(200) NOT NULL,
                          first_name varchar(80) NULL,
                          last_name varchar(90) NULL,
                          birthdate date NULL,
                          gender char(15) NULL,
                          picture varchar(300) NULL,
                          biography text NULL,
                          created_at timestamp NOT NULL,
                          updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          role_id int NOT NULL,
                          CONSTRAINT SM_Users_pk PRIMARY KEY (user_id)
);



-- Table: Crew
CREATE TABLE Crew (
                      crew_id int NOT NULL,
                      firt_name varchar(70) NOT NULL,
                      last_name varchar(50) NOT NULL,
                      email varchar(120) NULL,
                      profession varchar(100) NOT NULL,
                      biography text NULL,
                      created_at timestamp NOT NULL,
                      updated_at timestamp NOT NULL,
                      user_id int NOT NULL,
                      CONSTRAINT Crew_pk PRIMARY KEY (crew_id)
);

-- Table: Film
CREATE TABLE Film (
                      film_id int NOT NULL,
                      title varchar(200) NOT NULL,
                      duration time NOT NULL,
                      director varchar(120) NOT NULL,
                      pub_date timestamp NOT NULL,
                      episode int NULL,
                      cover blob NOT NULL,
                      summary text NOT NULL,
                      created_at int NOT NULL,
                      user_id int NOT NULL,
                      CONSTRAINT Film_pk PRIMARY KEY (film_id)
);

-- Table: FilmCrew
CREATE TABLE FilmCrew (
                          film_crew_id int NOT NULL,
                          created_at timestamp NOT NULL,
                          updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          film_id int NOT NULL,
                          crew_id int NOT NULL,
                          CONSTRAINT FilmCrew_pk PRIMARY KEY (film_crew_id)
);

-- Table: FilmGenre
CREATE TABLE FilmGenre (
                           film_genre_id int NOT NULL,
                           created_at timestamp NOT NULL,
                           updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           genre_id int NOT NULL,
                           film_id int NOT NULL,
                           CONSTRAINT FilmGenre_pk PRIMARY KEY (film_genre_id)
);

-- Table: Genre
CREATE TABLE Genre (
                       genre_id int NOT NULL,
                       title varchar(100) NOT NULL,
                       description text NOT NULL,
                       created_at timestamp NOT NULL,
                       updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       CONSTRAINT Genre_pk PRIMARY KEY (genre_id)
);


-- Table: Trailer
CREATE TABLE Trailer (
                         trailer_id int NOT NULL,
                         title varchar(150) NOT NULL,
                         author varchar(150) NULL,
                         duration time NOT NULL,
                         pub_date timestamp NOT NULL,
                         links varchar(150) NULL,
                         video blob NULL,
                         summary text NOT NULL,
                         created_at timestamp NOT NULL,
                         updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
                         user_id int NOT NULL,
                         CONSTRAINT Trailer_pk PRIMARY KEY (trailer_id)
);

-- Table: faq
CREATE TABLE faq (
                     faq_id int NOT NULL,
                     title varchar(150) NOT NULL,
                     description text NOT NULL,
                     created_at timestamp NOT NULL,
                     updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
                     user_id int NOT NULL,
                     CONSTRAINT faq_pk PRIMARY KEY (faq_id)
);

-- Table: BKCategory
CREATE TABLE BKCategory (
    bkCategory_id int NOT NULL,
    title varchar(100) NOT NULL,
    description text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT BKCategory_pk PRIMARY KEY (bkCategory_id)
);

-- Table: Book
CREATE TABLE Book (
    book_id int NOT NULL,
    title varchar(150) NOT NULL,
    isbn char(20) NOT NULL,
    author varchar(200) NOT NULL,
    pub_date date NULL,
    edition char(20) NULL,
    cover blob NULL,
    summary text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id int NOT NULL,
    CONSTRAINT Book_pk PRIMARY KEY (book_id)
);

-- Table: BookCategory
CREATE TABLE BookCategory (
    book_category_id int NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    book_id int NOT NULL,
    bkCategory_id int NOT NULL,
    CONSTRAINT BookCategory_pk PRIMARY KEY (book_category_id)
);

-- Table: Music
CREATE TABLE Music (
    music_id int NOT NULL,
    title varchar(120) NOT NULL,
    music_video blob NOT NULL,
    artist varchar(80) NULL,
    duration time NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id int NOT NULL,
    CONSTRAINT Music_pk PRIMARY KEY (music_id)
);


-- Table: ShortStory
CREATE TABLE ShortStory (
    short_story_id int NOT NULL,
    title varchar(150) NOT NULL,
    author varchar(200) NOT NULL,
    publication_date timestamp NOT NULL,
    descrition text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id int NOT NULL,
    CONSTRAINT ShortStory_pk PRIMARY KEY (short_story_id)
);


-- foreign keys
-- Reference: BookCategory_BKCategory (table: BookCategory)
ALTER TABLE BookCategory ADD CONSTRAINT BookCategory_BKCategory FOREIGN KEY BookCategory_BKCategory (bkCategory_id)
    REFERENCES BKCategory (bkCategory_id);

-- Reference: BookCategory_Book (table: BookCategory)
ALTER TABLE BookCategory ADD CONSTRAINT BookCategory_Book FOREIGN KEY BookCategory_Book (book_id)
    REFERENCES Book (book_id);

-- Reference: Book_SM_Users (table: Book)
ALTER TABLE Book ADD CONSTRAINT Book_SM_Users FOREIGN KEY Book_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: Crew_SM_Users (table: Crew)
ALTER TABLE Crew ADD CONSTRAINT Crew_SM_Users FOREIGN KEY Crew_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: FilmCrew_Crew (table: FilmCrew)
ALTER TABLE FilmCrew ADD CONSTRAINT FilmCrew_Crew FOREIGN KEY FilmCrew_Crew (crew_id)
    REFERENCES Crew (crew_id);

-- Reference: FilmCrew_Film (table: FilmCrew)
ALTER TABLE FilmCrew ADD CONSTRAINT FilmCrew_Film FOREIGN KEY FilmCrew_Film (film_id)
    REFERENCES Film (film_id);

-- Reference: FilmGenre_Film (table: FilmGenre)
ALTER TABLE FilmGenre ADD CONSTRAINT FilmGenre_Film FOREIGN KEY FilmGenre_Film (film_id)
    REFERENCES Film (film_id);

-- Reference: FilmGenre_Genre (table: FilmGenre)
ALTER TABLE FilmGenre ADD CONSTRAINT FilmGenre_Genre FOREIGN KEY FilmGenre_Genre (genre_id)
    REFERENCES Genre (genre_id);

-- Reference: Film_SM_Users (table: Film)
ALTER TABLE Film ADD CONSTRAINT Film_SM_Users FOREIGN KEY Film_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: Music_SM_Users (table: Music)
ALTER TABLE Music ADD CONSTRAINT Music_SM_Users FOREIGN KEY Music_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: SM_Users_Role (table: SM_Users)
ALTER TABLE SM_Users ADD CONSTRAINT SM_Users_Role FOREIGN KEY SM_Users_Role (role_id)
    REFERENCES Role (role_id);

-- Reference: ShortStory_SM_Users (table: ShortStory)
ALTER TABLE ShortStory ADD CONSTRAINT ShortStory_SM_Users FOREIGN KEY ShortStory_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: Trailer_SM_Users (table: Trailer)
ALTER TABLE Trailer ADD CONSTRAINT Trailer_SM_Users FOREIGN KEY Trailer_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- Reference: faq_SM_Users (table: faq)
ALTER TABLE faq ADD CONSTRAINT faq_SM_Users FOREIGN KEY faq_SM_Users (user_id)
    REFERENCES SM_Users (user_id);

-- End of file.

