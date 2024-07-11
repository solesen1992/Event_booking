package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DBLayer.BandDB;
import DBLayer.DataAccessException;
import DBLayer.EventDB;
import controllerLayer.EventCtr;
import model.Assignment;
import model.Band;
import model.Concert;
import model.Event;
import model.EventLocation;

/**
 * This is a test class for EventCtr. It contains unit tests for methods in the
 * EventCtr class.
 *
 * @author [Gruppe 3]
 * @version 1.0
 */

class EventCtrTest {
	private EventCtr eventCtr;
	private EventDB eventDB;
	private Concert event;
	private BandDB bandDB;

	@BeforeEach
	void setUp() throws SQLException, DataAccessException {
		eventCtr = new EventCtr(event);
		eventDB = new EventDB();
		bandDB = new BandDB();
	}

	/**
	 * 1 in the interaction diagram
	 * 
	 * This test checks if the event is created correctly. It verifies the event's
	 * type, title, start time, end time, and volunteer id.
	 * 
	 * @throws DataAccessException
	 */
	@Test
	void testCreateEvent() throws SQLException, DataAccessException {
		// The following lines define the data for the new event.
		String eventType = "koncert";
		String eventTitle = "store rock dag";
		LocalDateTime eventStartTime = LocalDateTime.of(2024, 06, 21, 18, 00);
		LocalDateTime eventEndTime = eventStartTime.plusHours(5);
		int volunteerId = 1;

		// An Event object is created with the previously defined data.
		Event createdEvent = eventCtr.createEventObject(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
		System.out.println("Test 1: " + createdEvent.getEventType());

		// A list of Event objects is created using the same data.
		List<Event> events = eventCtr.createEvent(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
		System.out.println("Test 1: " + events.size());

		// The following loop checks if the start and end times of the event are
		// correct.
		for (Event event : events) {
			System.out.println("Test 1: " + event.getEventType() + event.getEventStartTime() + event.getEventEndTime());

			// The test asserts that the event start time is either before or exactly at the
			// event end time.
			assertTrue(event.getEventStartTime().isBefore(eventEndTime)
					|| event.getEventStartTime().isEqual(eventEndTime));
			// The test asserts that the event end time is either after or exactly at the
			// event start time.
			assertTrue(
					event.getEventEndTime().isAfter(eventStartTime) || event.getEventEndTime().isEqual(eventStartTime));
		}

		// The following lines check if the created event is correct.

		// The test asserts that the list of events is not null.
		assertNotNull(events);
		// The test asserts that the size of the events list is 1.
		assertEquals(1, events.size());

		/**
		 * The next few lines check if the created event's details match the defined
		 * data by using the assertEquals method, which asserts that two objects are
		 * equal.
		 */
		assertEquals(eventType, createdEvent.getEventType());
		assertEquals(eventTitle, createdEvent.getEventTitle());
		assertEquals(eventStartTime, createdEvent.getEventStartTime());
		assertEquals(eventEndTime, createdEvent.getEventEndTime());
		// assertEquals(volunteerId, createdEvent.getVolunteerId());
	}

	/**
	 * 2 in the interaction diagram
	 * 
	 * This test suite is designed to check the functionality of the
	 * 'addBandsToConcert' method in the EventCtr class.
	 * 
	 * Checks the correctness of both the process of adding bands to 
	 * a concert and the accurate storage of band details in a concert object.
	 */
	@Test // This annotation lets JUnit know that this method is a test to be run.
	public void testAddBandsToConcert() throws SQLException {
		Event event = new Concert("Music", "Rock Concert", LocalDateTime.now(), LocalDateTime.now().plusHours(2), null,
				1);
		// Casts the event object to a Concert object
		Concert concert = (Concert) event;
		// Sets the event
		eventCtr.setEvent(event);
		// Verifies if the event object is created successfully
		eventCtr.verifyCreateEventObject();
		
		// We create two Band instances with different names.
		Band band1 = new Band("Metal", "5 medlemmer der elsker metal og blod", "SPIRACLE");
		Band band2 = new Band("Rock ", "3 medlemmer der er fest aber ", "Hudsult");

		// We create a list of the band names.
		List<String> bandNames = Arrays.asList(band1.getBandName(), band2.getBandName());

		// Adds the bandNames list to the concert.
		eventCtr.addBandsToConcert(bandNames);
		// Retrieves the list of bands that are added to the concert.
		ArrayList<Band> bandsOnConcert = concert.getBands();

		// Prints out the bands on the concert
		for (Band bandband : bandsOnConcert) {
			System.out.println(
					"test 2 " + bandband.getBandName() + bandband.getBandDescription() + bandband.getBandGenre());
		}

		// This loop checks if the added bands are correct.
		// Loop through each band in the concert
		for (int i = 0; i < concert.getBands().size(); i++) {
			// Get the actual band at index i
			Band actualBand = concert.getBands().get(i);

			// Get the expected band name at index i
			// Expected band names are the ones we added earlier. Band1 and Band2.
			String expectedBandName = bandNames.get(i);

			Band expectedBand = null;

			
			// Checks if the expected band name matches the name of band1 or band2 
			// to determine the expectedBand object
			if (expectedBandName.equals(band1.getBandName())) {
				expectedBand = band1;
			} else if (expectedBandName.equals(band2.getBandName())) {
				expectedBand = band2;
			}

			// We then use assertions to check that the method behaved as expected.
			// In this case, we're checking that the Concert's list of bands contains the
			// Band instances we created.
			assertEquals(bandNames.size(), concert.getBands().size()); // Assert there are 2 bands added

			assertEquals(expectedBand.getBandGenre(), actualBand.getBandGenre());
			assertEquals(expectedBand.getBandDescription(), actualBand.getBandDescription());
			assertEquals(expectedBand.getBandName(), actualBand.getBandName());
		}
	}

	/**
	 * 4 in the interaction diagram
	 * 
	 * Test of the method 'filterEventLocationOnCapacityAndAvailability'
	 * 
	 * This method creates an event object with specified attributes (time, date, estimatedAttendees).
	 * Then it filters event locations based on capacity and availability.
	 * Then it prints the number and names of available locations.
	 * 
	 * @throws DataAccessException
	 */
	@Test
	void testFilterEventLocationOnCapacityAndAvailability() throws SQLException, DataAccessException {
		// The following lines define the data for the new event.
		String eventType = "koncert";
		String eventTitle = "store rock dag";
		LocalDateTime eventStartTime = LocalDateTime.of(2024, 06, 21, 18, 00);
		LocalDateTime eventEndTime = eventStartTime.plusHours(5);
		int volunteerId = 1;
		Event createdEvent = eventCtr.createEventObject(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
		createdEvent.setEstimatedAttendees(50);

		System.out.println("Test 3 - : " + createdEvent.getEventType());

		// This line checks if there's available locations with enough capacity for the event.
		List<EventLocation> availableLocations = eventCtr.filterEventLocationOnCapacityAndAvailability();
		System.out.println("Test 3 - Antal ledige lokationer: " + availableLocations.size());

		// This loop prints out the available location names.
		for (EventLocation ell : availableLocations) {
			System.out.println("Test 3 - Navne på ledige lokationer: " + ell.getLocationName());
		}

	}


	/**
	 * Use case 2
	 * 3 in interaction diagram
	 * GUI layer: CreateAssignmentPart2GUI - Makes an assignment object and adds it to the event object
	 * Returns the assignment.
	 * 
	 * Tests addAndCreateAssignment.
	 * It sets up an event with specific detail.
	 * Then it creates an assignment and associates it with the EventId.
	 * When the assignment is added to the event, the test asserts that the newly 
	 * created assignment is present in the events list of assignments. 
	 * If the assignment is found, the test will pass.
	 * 
	 * */
	@Test
	void testAddAndCreateAssignment() throws SQLException, DataAccessException {
		//makes variables for the event
		int eventId = 2;
		String eventType = "koncert";
		String eventTitle = "Local Bands Live";
		LocalDateTime eventStartTime = LocalDateTime.of(2024, 06, 21, 18, 00);
		LocalDateTime eventEndTime = eventStartTime.plusHours(5);
		int volunteerId = 1;
		// Creates an event with the above parameters
		Event createdEvent = eventCtr.createEventObject(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
		
		//makes variables for the assignment
		String assignmentType = "rengøring";
		String assignmentDescription = "her skulle være en beskrivelse";
		LocalDateTime assignmentStartTime = LocalDateTime.of(2024, 06, 21, 18, 00);
		LocalDateTime assignmentEndTime = assignmentStartTime.plusHours(5);
		// Creates an assignment and add it to the event
		Assignment newAssignment = eventCtr.addAndCreateAssignment(assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, eventId);
		
		// Checks if the assignment is added to the event
		eventCtr.getEvent().getAssignments().contains(newAssignment);
		// Assert that the assignment was added to the event
		assertTrue(eventCtr.getEvent().getAssignments().contains(newAssignment));
	}
	
	

}
