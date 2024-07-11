package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DBLayer.DataAccessException;
import DBLayer.EventDB;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.Concert;
import model.Event;

/**
 * This is a set of JUnit tests for the `EventDB` class, which handles database operations 
 * related to events.
 * 
 * The `setUp` method initializes the `EventDB` object, and sets `eventStartTime` and `eventEndTime` 
 * to specific values. This method runs before each test.
 * 
 * The `testcheckExistingEventsOnDateAndTime` method tests the `checkExistingEventsOnDateAndTime` method 
 * of `EventDB`. It retrieves a list of events that occur during a specific date and time range, 
 * and verifies that the start and end times of each event are within this range.
 * 
 * The `testInsertCompletedEvent` method is an integration test that verifies the `insertCompletedEvent` 
 * method of `EventDB`. It creates an `Event` object, sets its properties, and then attempts to insert it 
 * into the database. After the insertion, it verifies that the returned `Event` object is not null and 
 * that its properties match the expected values.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class EventDBTest {
    private EventDB eventDB;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
	private String eventType;

	
	/**
	 * The `setUp` method initializes the `EventDB` object, and sets `eventStartTime` and `eventEndTime` 
	 * to specific values. This method runs before each test.
	 * */
    @BeforeEach
    void setUp() throws SQLException, DataAccessException {
        eventDB = new EventDB();
        eventStartTime = LocalDateTime.of(2024, 06, 20, 19, 00);
        eventEndTime = eventStartTime.plusHours(3);
    }

    /**
     * Test 1
     * 
     * This is a test method to check if there are existing events on a specific date and time.
     * 
     * Tests the `checkExistingEventsOnDateAndTime` method of `EventDB`. 
     * It retrieves a list of events that occur during a specific date and time range, 
     * and verifies that the start and end times of each event are within this range.
     * 
     * @throws DataAccessException 
     * */
    @Test
    void testcheckExistingEventsOnDateAndTime() throws SQLException, DataAccessException {
    	// Fetches a list of events that exist within specified start and end times.
    	List<Event> events = eventDB.checkExistingEventsOnDateAndTime(eventStartTime, eventEndTime);
        System.out.println("Test 1 - Size of the list: " + events.size());
        // This loop goes through each event in the list and prints their information
        for (Event event : events) {
        	System.out.println("Test 1: " + event.getEventType() +  event.getEventStartTime() + event.getEventEndTime());
        	
        	// This assertion checks if the event start time is before or equal to the end time.
        	assertTrue(event.getEventStartTime().isBefore(eventEndTime) || event.getEventStartTime().isEqual(eventEndTime));
            // This assertion check if the event end time is after or equal to the start time.
            assertTrue(event.getEventEndTime().isAfter(eventStartTime) || event.getEventEndTime().isEqual(eventStartTime));
        }
    }
    
    /**
     * Test 2
     * 
     * The `testInsertCompletedEvent` method is an integration test that verifies the `insertCompletedEvent` 
     * method of `EventDB`. It creates an `Event` object, sets its properties, and then attempts to insert it 
     * into the database. After the insertion, it verifies that the returned `Event` object is not null and 
     * that its properties match the expected values.
     */
    @Test //Intergrationstest
    void testInsertCompletedEvent() throws SQLException, DataAccessException {
        // Creates a sample event to insert
        Event event = new Concert(eventType, eventType, eventEndTime, eventEndTime, null, 0);
        event.setEventType("Koncert");
        event.setEventTitle("Jazz Concert");
        event.setEventStartTime(LocalDateTime.now());
        event.setEventEndTime(LocalDateTime.now().plusHours(2));
        event.setDescription("Leisurely chill time with Jazz");
        event.setEstimatedAttendees(80);
        event.setLocationId(3); // Assuming locationId exists in the database

        // Inserts the event into the database
        Event insertedEvent = eventDB.insertCompletedEvent(event);

        // Checks that the inserted event is not null
        assertNotNull(insertedEvent);

        // Checks some of the properties of the inserted event if needed. Are they equal to what we expect.
        assertEquals("Koncert", insertedEvent.getEventType());
        assertEquals("Jazz Concert", insertedEvent.getEventTitle());
    }

}