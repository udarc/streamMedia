DELETE  FROM Role;
DELETE FROM Trailer;
DELETE FROM faq;
DELETE  FROM FilmGenre;
DELETE FROM FilmCrew;
DELETE FROM Film;
DELETE FROM Genre;
DELETE FROM Crew;
DELETE FROM BookCategory;
DELETE FROM BKCategory;
DELETE FROM Book;
DELETE FROM  SM_Users;
INSERT INTO SM_Users(user_id,first_name, last_name, username, password,email,birthdate,created_at)
VALUES (1,'Joe','Coyne','jcoyne','supersecret1','jcoyne@streammedia.com','1964-04-01',NOW()),
(2,'Fred','Hensen','fhensen','supersecret2','fhensen@streammedia.com','1988-05-08',NOW()),
(3,'Barney','Curry','bcurry','supersecret3','bcurry@streammedia.com','1947-11-11',NOW()),
(4,'Karen','Mack','kmack','supersecret4','kmack@streammedia.com','1986-07-08',NOW()),
(5,'Dianne','Klein','dklein','supersecret5','dklein@streammedia.com','1991-09-22',NOW()),
(6,'Dawn','Tillman','dtillman','supersecret6','dtillman@streammedia.com','1979-08-30',NOW());

INSERT INTO Role(role_id,name,username , created_at) VALUES
(1,'user','jcoyne',Now()),
(2,'admin','fhensen',Now()),
(3,'developer','bcurry',Now()),
(4,'sale','kmack',Now()),
(5,'staff','bcurry',Now());


-- DROP TABLE IF EXISTS `Trailer`;
 INSERT INTO Trailer(trailer_id,title,author , duration,pub_date,summary,created_at, user) VALUES
(1,'Home Sweet Home','James','00:01:40',Now(),'Hibernate ORM is concerned with helping your application to achieve persistence.',Now(),1),
(2,'Hope','Henry','00:01:40',Now(), 'So what is persistence? Persistence simply means that we would like our application’s data to outlive the applications process.',Now(),6),
(3,'Peace','Chantal','00:01:40',Now(),' In Java terms, we would like the state of (some of) our objects to live beyond the scope of the JVM so that the same state is available later.',Now(),2),
(4,'The happiness','Nadine','00:01:40',Now(),'So what is persistence?',Now(),2),
(5,'Great Dane','Ella','00:01:40',Now(),'Hibernate ORM is concerned with helping your application to achieve persistence.',Now(),1);
-- DROP TABLE IF EXISTS `Trailer`;

INSERT INTO faq(faq_id,title,category,description ,created_at, user) VALUES
(1,'How to sell movies','Movie','This is a description of how to sell movies.',Now(),1),
(2,'How to access paid materials ','Sotories','Paid Materials are only accessible to users who have an account on this web site or pay for them where they are hosted.',NOW(),2),
(3,'Taking better photos','Movie',' In Java terms, we would like the state of (some of) our objects to live beyond the scope of the JVM so that the same state is available later.',Now(),2);

INSERT INTO Genre(genre_id,title,description ,created_at) VALUES
(1,'Romance','Love Related Movies',Now()),
(2,'Horror','Scary movies not recommend for children under 12 years old',NOW()),
(3,'Comedy','Funny Movies',Now()),
(4,'Adventure','Movies',Now());

INSERT INTO Crew(crew_id,first_name,last_name ,email,biography,profession,created_at,user) VALUES
(1,'Maureen','Patton',' mpatton@gmail.com','Some Bio for you','Student',Now(),2),
(2,'Priscilla','Campbell','pcampbell@gmail.com','Some Bio for you','Developer',NOW(),2),
(3,'Calvin','Huff', 'chuff@gmail.com','Some Bio for you','Teacher',Now(),2),
(4,'Gustavo','Diaz','gdiaz@example.com','Some Bio for you','Doctor',Now(),2),
(5,'Gertrude','Norman','gnorman@some.com','Some Bio for you','filmmaker',Now(),2);

INSERT INTO Film(film_id, title,duration,director,pub_date,summary,created_at,user, cover)
VALUES
(1,'Happy Day','01:39:02','Raymond Dawson','1986-07-08','Some Summary for Test',Now(),2,'some.png'),
(2,'Once upon time','01:39:02','Elizabeth Ingram','1986-07-08','Some Summary for Test',NOW(),2,'some.png'),
(3,'Calvin','01:39:02', 'Roberta Barnes','1986-07-08','Some Summary for Test',Now(),2,'some.png');
INSERT INTO FilmGenre(film_id,genre_id)
VALUES(1,2);
INSERT INTO FilmCrew(film_id,crew_id)
VALUES(1,5);


INSERT INTO BKCategory(bkCategory_id,title,description ,created_at) VALUES
(1,'Comic Book or Graphic Novel','The stories in comic books and graphic novels are presented to the reader through engaging, sequential narrative art (illustrations and typography) that''s either presented in a specific design or the traditional panel layout you find in comics.',Now()),
(2,'Detective and Mystery','The plot always revolves around a crime of sorts that must be solved—or foiled—by the protagonists.',NOW()),
(3,'Fantasy','While usually set in a fictional imagined world—in opposition, Ta-Nehisi''s Coates''s The Water Dancer takes place in the very real world of American slavery—fantasy books include prominent elements of magic, mythology, or the supernatural.',Now()),
(4,'Historical Fiction','These books are based in a time period set in the past decades, often against the backdrop of significant (real) historical events.',Now()),
(5,'Horror','Meant to cause discomfort and fear for both the character and readers, horror writers often make use of supernatural and paranormal elements in morbid stories that are sometimes a little too realistic.',Now()),
(6,'Literary Fiction','Though it can be seen as a broad genre that encompasses many others, literary fiction refers to the perceived artistic writing style of the author. Their prose is meant to evoke deep thought through stories that offer personal or social commentary on a particular theme.',Now());



INSERT INTO Book(book_id,title,isbn,author ,publisher, pub_date, summary, page_number, created_at, user_id) VALUES
(1,'A Christimas to remember','142532415261', 'Jane Doe', ' Hope',NOW(),'The  novels are presented to the reader through engaging, sequential narrative art (illustrations and typography) that''s either presented in a specific design or the traditional panel of Christimas.',435, NOW(),1),
(2,' The Mystery','24233115267','Fabien Jolie', 'Remy Happy',NOW(),'A crime of sorts that must be solved—or foiled—by the protagonists.',763, NOW(),2),
(3,'This is the day','5366252526','Becker James','Noella Newman',NOW(),'Second  Summary',1342, NOW(),1),
(4,'Asia History','9873772772', 'Nadia Calm','ISP Publisher',NOW(),'These books are based on Asia war.',416, NOW(),3);


INSERT INTO BookCategory(book_id,bkCategory_id)
VALUES(1,3),
       (2,5),
      (2,2),
       (4,2);