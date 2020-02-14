# DELETE FROM  SM_Users;
INSERT INTO SM_Users(user_id,first_name, last_name, username, password,email,birthdate,created_at)
VALUES (1,'Joe','Coyne','jcoyne','supersecret1','jcoyne@streammedia.com','1964-04-01',NOW()),
(2,'Fred','Hensen','fhensen','supersecret2','fhensen@streammedia.com','1988-05-08',NOW()),
(3,'Barney','Curry','bcurry','supersecret3','bcurry@streammedia.com','1947-11-11',NOW()),
(4,'Karen','Mack','kmack','supersecret4','kmack@streammedia.com','1986-07-08',NOW()),
(5,'Dianne','Klein','dklein','supersecret5','dklein@streammedia.com','1991-09-22',NOW()),
(6,'Dawn','Tillman','dtillman','supersecret6','dtillman@streammedia.com','1979-08-30',NOW());