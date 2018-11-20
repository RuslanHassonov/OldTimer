DROP TABLE cars;
DROP TABLE members;

CREATE TABLE members (
   memberID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   lastName varchar (25) NOT NULL,
   firstName varchar (25) NOT NULL,
   street varchar (25) NOT NULL,
   houseNumber int NOT NULL,
   zipCode int NOT NULL,
   city varchar(25) NOT NULL,
   country varchar(25) NOT NULL,
   phone varchar(25) DEFAULT ' ',
   mobile varchar(25) DEFAULT ' ',
   email varchar(25) DEFAULT ' ',
   
   PRIMARY KEY (memberID)
);

CREATE TABLE cars (
   carID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   memberNumber INT NOT NULL,
   carConstructor varchar (20) NOT NULL,
   carModel varchar (30) NOT NULL,
   buildYear int DEFAULT 0 CHECK(buildYear < 1990),
   carColor varchar(30) DEFAULT ' ',
   usageField varchar(30) DEFAULT ' ',
   
   PRIMARY KEY (carID),
   FOREIGN KEY (memberNumber) 
	REFERENCES members(memberID) 
	ON DELETE CASCADE
	ON UPDATE RESTRICT
);

INSERT INTO members (lastName, firstName, street, houseNumber, zipCode, city, country, phone, mobile, email )
VALUES 
   ('Deitel','Paul','This Street',5,1800,'Vilvoorde','Belgium','456-321987','9785/452145','paul.d@dreitel.com'), 
   ('Deitel','Harvey','That Street',10,1850,'Grimbergen','Belgium','654-987321',NULL,NULL),
   ('Deitel','Abbey','Amazing Lane',15,9870,'Weert','Netherlands',NULL,'7418/852639','abbey.d@dreitel.com'), 
   ('Morgano','Michael','Cool Avenue',20,7412,'Utrecht','Netherlands','319-852321','9371/873245','morgansen@dreitel.com');

INSERT INTO cars (memberNumber, carConstructor, carModel, buildYear, carColor, usageField)
VALUES 
   (1,'Ye Olde Cars','Hillbilly',1964,'Red',NULL), 
   (1,'Prospector','Gold Digger',NULL,'Gold',NULL),
   (2,'Rattler','Loudey',1979,'Orange',NULL), 
   (3,'Prospector','Miner',1955,NULL,NULL),
   (4,'Rancher','Sheep Wrangler',1932, 'White',NULL);

