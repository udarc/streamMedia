-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-02-07 17:10:33.785

-- foreign keys
ALTER TABLE BookCategory
    DROP FOREIGN KEY BookCategory_BKCategory;

ALTER TABLE BookCategory
    DROP FOREIGN KEY BookCategory_Book;

ALTER TABLE Book
    DROP FOREIGN KEY Book_SM_Users;

ALTER TABLE Crew
    DROP FOREIGN KEY Crew_SM_Users;

ALTER TABLE FilmCrew
    DROP FOREIGN KEY FilmCrew_Crew;

ALTER TABLE FilmCrew
    DROP FOREIGN KEY FilmCrew_Film;

ALTER TABLE FilmGenre
    DROP FOREIGN KEY FilmGenre_Film;

ALTER TABLE FilmGenre
    DROP FOREIGN KEY FilmGenre_Genre;

ALTER TABLE Film
    DROP FOREIGN KEY Film_SM_Users;

ALTER TABLE Music
    DROP FOREIGN KEY Music_SM_Users;

ALTER TABLE SM_Users
    DROP FOREIGN KEY SM_Users_Role;

ALTER TABLE ShortStory
    DROP FOREIGN KEY ShortStory_SM_Users;

ALTER TABLE Trailer
    DROP FOREIGN KEY Trailer_SM_Users;

ALTER TABLE faq
    DROP FOREIGN KEY faq_SM_Users;

-- tables
DROP TABLE BKCategory;

DROP TABLE Book;

DROP TABLE BookCategory;

DROP TABLE Crew;

DROP TABLE Film;

DROP TABLE FilmCrew;

DROP TABLE FilmGenre;

DROP TABLE Genre;

DROP TABLE Music;

DROP TABLE Role;

DROP TABLE SM_Users;

DROP TABLE ShortStory;

DROP TABLE Trailer;

DROP TABLE faq;

-- End of file.

