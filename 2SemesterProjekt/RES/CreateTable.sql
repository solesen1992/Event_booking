USE [DMA-CSD-S233_10510643];
--create tables

CREATE TABLE CityZipCode(
cityZipCodeId INT IDENTITY(1,1),
zipCode INT NOT NULL,
city VARCHAR(255) NOT NULL,
PRIMARY KEY(cityZipCodeId)
); 

CREATE TABLE Volunteer(
volunteerId INT IDENTITY(1,1),
firstName VARCHAR(15) NOT NULL,
lastName VARCHAR(15) NOT NULL,
phoneNo VARCHAR(12) NOT NULL,
email VARCHAR(255) NOT NULL,
street VARCHAR(255),
memberType VARCHAR(255) NOT NULL, 
role VARCHAR(255),
fk_cityZipCodeId INT,
PRIMARY KEY(volunteerId),
FOREIGN KEY(fk_cityZipCodeId) REFERENCES CityZipCode(cityZipCodeId)
); 


CREATE TABLE Brewery(
breweryId INT IDENTITY(1,1),
beerType VARCHAR(255) NOT NULL,
amountOfBeer INT NOT NULL,
fk_volunteerId INT,
PRIMARY KEY(breweryId),
FOREIGN KEY(fk_volunteerId) REFERENCES Volunteer(volunteerId)
);


CREATE TABLE EventLocation(
locationId INT IDENTITY(1,1),
locationName VARCHAR(255) NOT NULL,
locationStreet VARCHAR(255) NOT NULL,
locationEmail VARCHAR(255) NOT NULL,
locationPhoneNo VARCHAR(12) NOT NULL,
capacity INT NOT NULL,
fk_cityZipCodeId INT,
PRIMARY KEY(locationId),
FOREIGN KEY(fk_cityZipCodeId) REFERENCES CityZipCode(cityZipCodeId)
);	



CREATE TABLE Event(
eventId INT IDENTITY(1,1),
eventType VARCHAR(255) NOT NULL,
eventTitle VARCHAR(255) NOT NULL,
eventDate DATE NOT NULL,
eventStartTime DATETIME NOT NULL,
eventEndTime DATETIME NOT NULL,
eventDescription VARCHAR(255),
volunteerCount INT,
estimatedAttendees INT NOT NULL,
fk_locationId INT,
fk_volunteerId INT,
PRIMARY KEY(eventId),
FOREIGN KEY(fk_locationId) REFERENCES EventLocation(locationId),
FOREIGN KEY(fk_volunteerId) REFERENCES Volunteer(volunteerId)
);


CREATE TABLE Bar(
fk_barEventId INT,
drinkDiscount DECIMAL(10,2),
PRIMARY KEY(fk_barEventId),
FOREIGN KEY(fk_barEventId) REFERENCES Event(eventId)
); 


CREATE TABLE CommunityKitchen(
fk_kitchenEventId INT,
menu VARCHAR(255) NOT NULL,
mealPrice INT NOT NULL,
PRIMARY KEY(fk_kitchenEventId),
FOREIGN KEY(fk_kitchenEventId) REFERENCES Event(eventId)
);

CREATE TABLE Cinema(
fk_cinemaEventId INT,
movie VARCHAR(255) NOT NULL,
movieGenre VARCHAR(255) NOT NULL,
PRIMARY KEY(fk_cinemaEventId),
FOREIGN KEY(fk_cinemaEventId) REFERENCES Event(eventId)
);



CREATE TABLE Concert(
fk_concertEventId INT,
ticketPrice INT NOT NULL,
PRIMARY KEY(fk_concertEventId),
FOREIGN KEY(fk_concertEventId) REFERENCES Event(eventId)
);


CREATE TABLE Band(
bandId INT IDENTITY(1,1),
bandGenre VARCHAR(255) NOT NULL,
bandDescription VARCHAR(255) NOT NULL,
PRIMARY KEY(bandId),
);



CREATE TABLE ConcertBand(
fk_bandId INT,
fk_concertEventId INT,
PRIMARY KEY(fk_bandId, fk_concertEventId),
FOREIGN KEY(fk_bandId) REFERENCES Band(bandId),
FOREIGN KEY(fk_concertEventId) REFERENCES Concert(fk_concertEventId),
);



CREATE TABLE Assignment(
assignmentId INT IDENTITY(1,1),
assignmentType VARCHAR(255) NOT NULL,
assignmentDescription VARCHAR(255) NOT NULL,
assignmentStartTime DATETIME NOT NULL,
assignmentEndTime DATETIME NOT NULL,
fk_eventId INT,
PRIMARY KEY(assignmentId),
FOREIGN KEY(fk_eventId) REFERENCES Event(eventId),
);



CREATE TABLE VolunteerAssignment(
fk_volunteerId INT,
fk_assigmentId INT,
PRIMARY KEY(fk_volunteerId, fk_assigmentId),
FOREIGN KEY(fk_volunteerId) REFERENCES Volunteer(volunteerId),
FOREIGN KEY(fk_assigmentId) REFERENCES Assignment (assignmentId),
);


ALTER TABLE Volunteer
ALTER COLUMN role VARCHAR(255) NULL;


ALTER TABLE Band
ADD bandName VARCHAR(255);

UPDATE Band
SET bandName = 'SPIRACLE'
WHERE bandId = 1;

UPDATE Band
SET bandName = 'Foreshadower'
WHERE bandId = 2;

UPDATE Band
SET bandName = 'Hudsult'
WHERE bandId = 3;


Alter table Event
drop column eventDate;