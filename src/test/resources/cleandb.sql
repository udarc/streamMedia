#
DELETE  FROM Role;
DELETE FROM  `Trailer`;
DELETE FROM faq;
DELETE FROM  SM_Users;
DELETE FROM Genre;
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
(3,'Comedy','Funny Movies',Now());


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
