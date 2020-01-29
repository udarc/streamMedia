-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-01-29 04:29:50.156

-- tables
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
    updated_at timestamp NOT NULL,
    USERS_user_id int NOT NULL,
    CONSTRAINT Book_pk PRIMARY KEY (book_id)
);

-- Table: BookCategory
CREATE TABLE BookCategory (
    book_category_id int NOT NULL,
    Book_book_id int NOT NULL,
    bk_category_bk_category_id int NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT BookCategory_pk PRIMARY KEY (book_category_id)
);

-- Table: CastingCrew
CREATE TABLE CastingCrew (
    casting_crew int NOT NULL,
    firt_name varchar(70) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(120) NULL,
    profession varchar(100) NOT NULL,
    biography text NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    USERS_user_id int NOT NULL,
    CONSTRAINT CastingCrew_pk PRIMARY KEY (casting_crew)
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
    USERS_user_id int NOT NULL,
    CONSTRAINT Film_pk PRIMARY KEY (film_id)
);

-- Table: FilmCastingCrew
CREATE TABLE FilmCastingCrew (
    film_casting_crew_id int NOT NULL,
    Film_film_id int NOT NULL,
    CastingCrew_casting_crew int NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT FilmCastingCrew_pk PRIMARY KEY (film_casting_crew_id)
);

-- Table: FilmGenre
CREATE TABLE FilmGenre (
    film_genre_id int NOT NULL,
    Film_film_id int NOT NULL,
    Genre_genre_id int NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT FilmGenre_pk PRIMARY KEY (film_genre_id)
);

-- Table: Genre
CREATE TABLE Genre (
    genre_id int NOT NULL,
    title varchar(100) NOT NULL,
    description text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT Genre_pk PRIMARY KEY (genre_id)
);

-- Table: Music
CREATE TABLE Music (
    music_id int NOT NULL,
    title varchar(120) NOT NULL,
    music_video blob NOT NULL,
    artist varchar(80) NULL,
    duration time NOT NULL,
    USERS_user_id int NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT Music_pk PRIMARY KEY (music_id)
);

-- Table: ROLES
CREATE TABLE ROLES (
    role_id int NOT NULL AUTO_INCREMENT,
    name char(30) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT ROLES_pk PRIMARY KEY (role_id)
);

-- Table: ShortStory
CREATE TABLE ShortStory (
    short_story_id int NOT NULL,
    title varchar(150) NOT NULL,
    author varchar(200) NOT NULL,
    publication_date timestamp NOT NULL,
    descrition text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    USERS_user_id int NOT NULL,
    CONSTRAINT ShortStory_pk PRIMARY KEY (short_story_id)
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
    updated_at timestamp NOT NULL,
    USERS_user_id int NOT NULL,
    CONSTRAINT Trailer_pk PRIMARY KEY (trailer_id)
);

-- Table: USERS
CREATE TABLE USERS (
    user_id int NOT NULL AUTO_INCREMENT,
    username varchar(40) NOT NULL,
    email varchar(120) NOT NULL,
    password varchar(200) NOT NULL,
    first_name varchar(80) NULL,
    last_name varchar(90) NULL,
    birthdate date NULL,
    picture blob NULL,
    biography text NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    ROLES_role_id int NOT NULL,
    CONSTRAINT USERS_pk PRIMARY KEY (user_id)
);

-- Table: bk_category
CREATE TABLE bk_category (
    bk_category_id int NOT NULL,
    title varchar(100) NOT NULL,
    description text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT bk_category_pk PRIMARY KEY (bk_category_id)
);

-- Table: faq
CREATE TABLE faq (
    faq_id int NOT NULL,
    title varchar(150) NOT NULL,
    description text NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    USERS_user_id int NOT NULL,
    CONSTRAINT faq_pk PRIMARY KEY (faq_id)
);

-- foreign keys
-- Reference: BookCategory_Book (table: BookCategory)
ALTER TABLE BookCategory ADD CONSTRAINT BookCategory_Book FOREIGN KEY BookCategory_Book (Book_book_id)
    REFERENCES Book (book_id);

-- Reference: BookCategory_bk_category (table: BookCategory)
ALTER TABLE BookCategory ADD CONSTRAINT BookCategory_bk_category FOREIGN KEY BookCategory_bk_category (bk_category_bk_category_id)
    REFERENCES bk_category (bk_category_id);

-- Reference: Book_USERS (table: Book)
ALTER TABLE Book ADD CONSTRAINT Book_USERS FOREIGN KEY Book_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: CastingCrew_USERS (table: CastingCrew)
ALTER TABLE CastingCrew ADD CONSTRAINT CastingCrew_USERS FOREIGN KEY CastingCrew_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: FilmCastingCrew_CastingCrew (table: FilmCastingCrew)
ALTER TABLE FilmCastingCrew ADD CONSTRAINT FilmCastingCrew_CastingCrew FOREIGN KEY FilmCastingCrew_CastingCrew (CastingCrew_casting_crew)
    REFERENCES CastingCrew (casting_crew);

-- Reference: FilmCastingCrew_Film (table: FilmCastingCrew)
ALTER TABLE FilmCastingCrew ADD CONSTRAINT FilmCastingCrew_Film FOREIGN KEY FilmCastingCrew_Film (Film_film_id)
    REFERENCES Film (film_id);

-- Reference: FilmGenre_Film (table: FilmGenre)
ALTER TABLE FilmGenre ADD CONSTRAINT FilmGenre_Film FOREIGN KEY FilmGenre_Film (Film_film_id)
    REFERENCES Film (film_id);

-- Reference: FilmGenre_Genre (table: FilmGenre)
ALTER TABLE FilmGenre ADD CONSTRAINT FilmGenre_Genre FOREIGN KEY FilmGenre_Genre (Genre_genre_id)
    REFERENCES Genre (genre_id);

-- Reference: Film_USERS (table: Film)
ALTER TABLE Film ADD CONSTRAINT Film_USERS FOREIGN KEY Film_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: Music_USERS (table: Music)
ALTER TABLE Music ADD CONSTRAINT Music_USERS FOREIGN KEY Music_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: ShortStory_USERS (table: ShortStory)
ALTER TABLE ShortStory ADD CONSTRAINT ShortStory_USERS FOREIGN KEY ShortStory_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: Trailer_USERS (table: Trailer)
ALTER TABLE Trailer ADD CONSTRAINT Trailer_USERS FOREIGN KEY Trailer_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- Reference: USERS_ROLES (table: USERS)
ALTER TABLE USERS ADD CONSTRAINT USERS_ROLES FOREIGN KEY USERS_ROLES (ROLES_role_id)
    REFERENCES ROLES (role_id);

-- Reference: faq_USERS (table: faq)
ALTER TABLE faq ADD CONSTRAINT faq_USERS FOREIGN KEY faq_USERS (USERS_user_id)
    REFERENCES USERS (user_id);

-- End of file.

