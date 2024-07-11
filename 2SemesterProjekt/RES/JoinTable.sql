SELECT *
FROM Event
LEFT JOIN Bar
ON Event.eventId = Bar.fk_barEventId;

SELECT *
FROM Event
LEFT JOIN Bar
ON Event.eventId = Bar.fk_barEventId;

SELECT *
FROM Event
LEFT JOIN Bar
ON Event.eventId = Bar.fk_barEventId
WHERE Event.eventType = 'bar';

SELECT *
FROM Event
LEFT JOIN Concert
ON Event.eventId = Concert.fk_concertEventId
WHERE Event.eventType = 'koncert';

SELECT *
FROM Event
LEFT JOIN CommunityKitchen
ON Event.eventId = CommunityKitchen.fk_kitchenEventId
WHERE Event.eventType = 'Folkekøkken';

SELECT *
FROM Event
LEFT JOIN Cinema
ON Event.eventId = Cinema.fk_cinemaEventId
WHERE Event.eventType = 'biograf';

SELECT *
FROM EventLocation
INNER JOIN CityZipCode ON EventLocation.fk_cityZipCodeId = CityZipCode.cityZipCodeId;


SELECT EventLocation.locationName, Event.eventType, Event.eventStartTime, Event.eventEndTime
FROM EventLocation
INNER JOIN Event ON EventLocation.locationId = Event.fk_locationId;

SELECT e.eventId, e.eventType, e.eventTitle, e.eventStartTime, e.eventEndTime, e.eventDescription, e.volunteerCount, e.estimatedAttendees, el.locationName
FROM Event e
INNER JOIN EventLocation el ON e.fk_locationId = el.locationId;