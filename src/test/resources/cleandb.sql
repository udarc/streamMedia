#
DELETE  FROM Role;
DELETE FROM  `Trailer`;
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