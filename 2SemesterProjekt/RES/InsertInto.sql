INSERT INTO CityZipCode (zipCode, city) VALUES(9000, 'Aalborg');

DBCC checkident ('CityZipCode', reseed, 0);

INSERT INTO Volunteer (firstName, lastName, phoneNo, email, street, memberType, role, fk_cityZipCodeId) VALUES ('Jens', 'Jensen', '12345678', 'jens.jensen@email.dk', 'Møllegade 3', 'Medlem', 'Bestyrelse',1);
INSERT INTO Volunteer (firstName, lastName, phoneNo, email, street, memberType, role, fk_cityZipCodeId) VALUES ('Hanne', 'Hansen', '23456789', 'hanne.hansen@email.dk', 'Kongensgade 5', 'Medlem', 'Formand',1); 
INSERT INTO Volunteer (firstName, lastName, phoneNo, email, street, memberType, role, fk_cityZipCodeId) VALUES ('Peter', 'Petersen', '34567891', 'peter.petersen@email.dk', 'Østergade 10', 'Frivillig', NULL,1); 
INSERT INTO Volunteer (firstName, lastName, phoneNo, email, street, memberType, role, fk_cityZipCodeId) VALUES ('Lise', 'Larsen', '45678912', 'lise.larsen@email.dk', 'Vestergade 15', 'Frivillig', NULL,1);
DBCC checkident ('Volunteer', reseed, 0);
Delete from volunteer;
select* from volunteer;

INSERT INTO Brewery (beerType, amountOfBeer, fk_volunteerId) VALUES ('Ale No 16', 100, NULL);
INSERT INTO Brewery (beerType, amountOfBeer, fk_volunteerId) VALUES ('Porter', 10, NULL);
INSERT INTO Brewery (beerType, amountOfBeer, fk_volunteerId) VALUES ('Erdinger', 44, NULL);
DBCC checkident ('Brewery', reseed, 0);
Delete from Brewery;
select* from Brewery;

INSERT INTO EventLocation(locationName, locationStreet, locationEmail, locationPhoneNo, capacity) VALUES('1000fryd café', 'Kattesundet 10', '1000fryd@1000fryd.dk', '004598132221', 1);
INSERT INTO EventLocation(locationName, locationStreet, locationEmail, locationPhoneNo, capacity) VALUES('1000fryd biograf', 'Kattesundet 10', '1000fryd@1000fryd.dk', '004598132221', 1);
INSERT INTO EventLocation(locationName, locationStreet, locationEmail, locationPhoneNo, capacity) VALUES('Rocken', 'Ved Stranden 11D', 'rockenaalborg@gmail.com', '004541930301', 1);
INSERT INTO EventLocation(locationName, locationStreet, locationEmail, locationPhoneNo, capacity) VALUES('Brolandingerne ', 'Strandvejen 10', 'mailaalborg@gmail.com', '004541930302', 1);
DBCC checkident ('EventLocation', reseed, 0);
Delete from EventLocation;
select* from EventLocation;
Update EventLocation
Set fk_cityZipCodeId = 1
WHERE locationId = 1;
Update EventLocation
Set fk_cityZipCodeId = 1
WHERE locationId = 2;
Update EventLocation
Set fk_cityZipCodeId = 1
WHERE locationId = 3;
Update EventLocation
Set fk_cityZipCodeId = 1
WHERE locationId = 4;

Update EventLocation
Set capacity = 100
WHERE locationId = 1;
Update EventLocation
Set capacity = 50
WHERE locationId = 2;
Update EventLocation
Set capacity = 80
WHERE locationId = 3;
Update EventLocation
Set capacity = 30
WHERE locationId = 4;

INSERT INTO Event (eventType, eventTitle, eventDate, eventStartTime, eventEndTime, eventDescription, volunteerCount, estimatedAttendees, fk_locationId, fk_volunteerId) VALUES ('Biograf', 'Film Night', '2022-05-20', '19:00:00', '22:00:00', 'A movie night for local community.', 10, 100,2, NULL); 
INSERT INTO Event (eventType, eventTitle, eventDate, eventStartTime, eventEndTime, eventDescription, volunteerCount, estimatedAttendees, fk_locationId, fk_volunteerId) VALUES ('Koncert', 'Local Bands Live', '2022-06-15', '18:00:00', '23:00:00', 'A concert featuring local bands.', 15, 200, NULL, NULL); 
INSERT INTO Event (eventType, eventTitle, eventDate, eventStartTime, eventEndTime, eventDescription, volunteerCount, estimatedAttendees, fk_locationId, fk_volunteerId) VALUES ('Folkekøkken', 'Community Dinner', '2022-07-10', '18:00:00', '21:00:00', 'A community dinner for local residents.', 20, 150,1, NULL); 
INSERT INTO Event (eventType, eventTitle, eventDate, eventStartTime, eventEndTime, eventDescription, volunteerCount, estimatedAttendees, fk_locationId, fk_volunteerId) VALUES ('Bar', 'Friday Social', '2022-08-05', '20:00:00', '02:00:00', 'A social gathering at the local bar.', 5, 80,1, NULL);
DBCC checkident ('Event', reseed, 0);
Delete from Event;
select* from Event;
Update Event
Set eventStartTime = '2024-06-20 19:00', eventEndTime = '2024-06-20 22:00'
WHERE eventId = 1;
Update Event
Set eventStartTime = '2024-06-21 18:00', eventEndTime = '2024-06-21 23:00'
WHERE eventId = 2;
Update Event
Set eventStartTime = '2024-06-22 18:00', eventEndTime = '2024-06-22 21:00'
WHERE eventId = 3;
Update Event
Set eventStartTime = '2024-06-28 20:00', eventEndTime = '2024-06-29 02:00'
WHERE eventId = 4;
Update Event
Set fk_locationId = 1
WHERE eventId = 2;

INSERT INTO Bar(fk_barEventId, drinkDiscount) VALUES(4, 10.0);
select* from Bar;

INSERT INTO CommunityKitchen (fk_kitchenEventId, menu, mealPrice) VALUES (3,'Vegansk lasagne', 25); 
select* from CommunityKitchen;

INSERT INTO Cinema (fk_cinemaEventId, movie, movieGenre) VALUES (1,'Attack of the Killer Tomatoes', 'Comedy');
select* from Cinema;

INSERT INTO Concert (fk_concertEventId, ticketPrice) VALUES(2,50);
select* from Concert ;

INSERT INTO Band (bandGenre, bandDescription) VALUES('Metal', '5 medlemmer der elsker metal og blod');
INSERT INTO Band (bandGenre, bandDescription) VALUES ( 'Rock ',  ' 4 medlemmer og en masse passion');
INSERT INTO Band (bandGenre, bandDescription) VALUES( 'Rock ',  '3 medlemmer der er fest aber ');
select * from Band;

INSERT INTO ConcertBand (fk_bandId,fk_concertEventId) VALUES(1,2);
select * from ConcertBand;

INSERT INTO Assignment (assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, fk_eventId) VALUES ('Vagttjeneste', 'Sikkerhedsvagt i hovedindgangen', '2022-05-20 18:00:00', '2022-05-20 22:00:00', 2)
INSERT INTO Assignment (assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, fk_eventId) VALUES ('Rengøring', 'Rengøring af eventområde efter afslutning', '2022-06-15 23:00:00', '2022-06-16 01:00:00',1)
INSERT INTO Assignment (assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, fk_eventId) VALUES ('Servering', 'Servering af mad til gæster', '2022-07-10 18:00:00', '2022-07-10 21:00:00', 3)
INSERT INTO Assignment (assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, fk_eventId) VALUES ('Barvagt', 'Servering af drikkevarer i baren', '2022-08-05 20:00:00', '2022-08-06 02:00:00',4);
select * from Assignment;

INSERT INTO VolunteerAssignment (fk_volunteerId, fk_assigmentId) VALUES(3, 1);
INSERT INTO VolunteerAssignment (fk_volunteerId, fk_assigmentId) VALUES(4, 4);
select * from VolunteerAssignment;


select * from Assignment