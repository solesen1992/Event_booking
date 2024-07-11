
package controllerLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DBLayer.BandDB;
import DBLayer.BandDBIF;
import DBLayer.DataAccessException;
import DBLayer.EventDB;
import DBLayer.EventDBIF;
import model.Assignment;
import model.Band;
import model.Bar;
import model.Cinema;
import model.CommunityKitchen;
import model.Concert;
import model.Event;
import model.EventLocation;
import model.Volunteer;

/**
 * The controller class for the Event object.
 * 
 * Some of the methods include: 1) eventObjectCreation creates a new Event
 * instance. 2) createEvent creates a new Event and adds it to a list of events.
 * 3) verifyConcertObjectCreation checks if an Event object has been created and
 * if it is a Concert. 4) addConcertInformation adds information to a Concert.
 * 5) confirmBand checks for event location availability based on the estimated
 * attendees. 6) setEventLocation sets the location for the event. 7)
 * completedEvent inserts a completed Event into the database. 8) showAllEvents
 * retrieves all Events from the database. 9) findAllBands retrieves all Bands
 * from the database.
 * 
 * This class interacts with the Event Database Interface (`EventDBIF`), Band
 * Database Interface (`BandDBIF`), and other controller classes like
 * `EventLocationCtr` and `VolunteerCtr`.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class EventCtr  {
	private EventDBIF eventDB;
	private BandDBIF bandDB;
	private EventLocationCtr eventLocationCtr;
	private VolunteerCtr volunteerCtr;
	private Event event;
	private List<Event> eventList;
	private AssignmentCtr assignmentCtr;
 //holds all the confirmed Assignments

	/**
	 * Constructor for Event Controller class
	 * Event is an abstract class so we can't make it into an object.
	 * 
	 * @param event
	 * @throws DataAccessException
	 */
	public EventCtr(Event event) throws DataAccessException {
		bandDB = new BandDB();
		eventDB = new EventDB();
		this.event = event;
		this.eventList = new ArrayList<Event>();
		eventLocationCtr = new EventLocationCtr();
		this.assignmentCtr =  new AssignmentCtr();
		volunteerCtr = new VolunteerCtr();
	
	} 
		
	
	/*
	 * Use case 1 - Create event
	 * 1 in interaction diagram
	 * 
	 * This method is responsible for creating a new event and checking if there's any 
	 * existing events that conflict with the time and date of the new event.
	 * 
	 * @return eventList
	 */
	public List<Event> createEvent(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, int volunteerId) throws DataAccessException {
		//calls the method createEventObject with the provided parameters
		//it's a specific event we need to create - takes the parameters from the user with it
		createEventObject(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
		//Checks if there's any existing events that conflicts with the new events time
		eventList = eventDB.checkExistingEventsOnDateAndTime(eventStartTime, eventEndTime);

		//returns a list of events that conflicts with the new event
		//if the list is empty, there's no conflicting events.
		return eventList;
	}
	
	/**
	 * Use case 1 - Create event
	 * 1 in the interaction diagram
	 * 
	 * Method for creating an event object based on the provided parameters. 
	 * It creates a specific event object based on the event type and returns it.
	 *
	 */
	public Event createEventObject(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, int volunteerId) {
		// Checks if the volunteer ID from the Volunteer object in VolunteerCtr 
		// is equal to the provided volunteerId
		if( volunteerCtr.getVolunteer().getVolunteerId() == volunteerId) {
			//// Gets the Volunteer object from VolunteerCtr
			Volunteer volunteer = volunteerCtr.getVolunteer();
			
		// If the event type is "koncert", create a new Concert object
		// 0 is the eventId. It get's auto generated in the database.
		if (eventType.equalsIgnoreCase("koncert"))
			event = new Concert(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,0);
		// If the event type is "bar", create a new Bar object
		else if (eventType.equalsIgnoreCase("bar"))
			event = new Bar(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,0);
		// If the event type is "biograf", create a new Cinema object
		else if (eventType.equalsIgnoreCase("biograf"))
			event = new Cinema(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,0);
		// If the event type is "folkekøkken", create a new CommunityKitchen object
		else if (eventType.equalsIgnoreCase("folkekøkken"))
			event = new CommunityKitchen(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,0);
		}
		// Returns the newly created event object
		return event;
	}
	
	
	/**
	 * Use case 1 - Create Event
	 * 2 in the interaction diagram
	 * 
	 * This method is used to add a list of bands / multiple bands to a concert based on bandNames.
	 * We get the bandNames list parameter from the GUI layer. 
	 * The user adds the names to a list.
	 */
	public void addBandsToConcert(List<String> bandNames) {
		//The event is cast to a Concert object since only concerts can have bands.
		//It makes a new concert object by casting the event to a concert type.
		//Converts one data type to another one.
		Concert concert = (Concert) event;
		//A list that will hold all the Band objects retrieved from the database.
		List<Band> allBands = new ArrayList<>();
		
		//We iterate through the bandNames that are passed into the method as a parameter.
		for (String bandName : bandNames) {
			try {
				//For each band name it tries to find matching bands in the database.
				List<Band> bands = bandDB.findBandsByNames(bandName);
				//If any bands are found, they're added to the allBands list.
				allBands.addAll(bands);
			
			} catch (DataAccessException e) {
				//If an error occurs while trying to access the database, the stack trace is printed.
				e.printStackTrace();
			}
		}

		//When all bands have been retrieved, it loops through the allBands list.
		for (Band band : allBands) {
			//The bands are added to the concert.
			concert.addBand(band);
		}
	}


	/**
	 * Use case 1 - Create event
	 * 3 in the interaction diagram
	 * 
	 * These methods adds information to the different types of events.
	 * First, it verifies if there's an instance of a specific event type 
	 * (see method: verifyCreatedEventObject).
	 * If there's an instance, it sets EstimatedAttentees, price etc.
	 */
	
	//Adds information to a concert event
	public void addConcertInformation(int estimatedAttendees, double price, String description) {
		verifyCreateEventObject();
		setEstimatedAttendeesToEvent(estimatedAttendees);
		setPriceToConcert(price);
		setDescriptionToEvent(description);

	}
	
	//Adds information to a bar event
	public void addBarInformation(int estimatedAttendees, double drinkDiscount, String description) {
		verifyCreateEventObject();
		setEstimatedAttendeesToEvent(estimatedAttendees);
		setDrinkDiscountToBar(drinkDiscount);
		setDescriptionToEvent(description);
	}
	
	//Adds information to a cinema event
	public void addCinemaInformation(int estimatedAttendees, String movie, String movieGenre, String description) {
		verifyCreateEventObject();
		setEstimatedAttendeesToEvent(estimatedAttendees);
		setMovieToCinema(movie);
		setMovieGenreToCinema(movieGenre);
		setDescriptionToEvent(description);
	}
	
	/**
	 * Use case 1 - Create event
	 * 2 in the interaction diagram
	 * 
	 * This method checks if the event object has been created and if it's an instance
	 * of Concert, Bar, Cinema or CommunityKitchen. We do so before setting the different parameters
	 * in the addConcertInformation, addBarInformation, addCinema information etc. methods.
	 * 
	 * We check for an existing object to ensure there's a valid object before attempting to perform
	 * operations on it. If we try to perform an operation on an null object, it will result in an 
	 * NullPointerException error, causing the program to crash. That's why we check if an object
	 * exist before trying to perform operations on it.
	 */
	public void verifyCreateEventObject() throws IllegalArgumentException {
		//If the event object isn't created, it throws an IllegalArgumentException
		if (event == null) {
			throw new IllegalArgumentException("Event er ikke oprettet.");
		}
		//If the event is an instance of Concert, Bar, Cinema or CommunityKitchen
		//the method stops executing at this point and leaves the method.
		if (event instanceof Concert || event instanceof Bar || event instanceof Cinema || event instanceof CommunityKitchen){
			return;
		}
		//If the event object is not an instance of Concert etc.
		else{
			throw new IllegalArgumentException("Event skal være en instans af Concert, Bar, Cinema, eller CommunityKitchen.");
		}
	}
	
	
	/**
	 * Use case 1 - Create event
	 * 3.1 in the interaction diagram 
	 * Method to set the estimated attendees of a event.
	 */
	private void setEstimatedAttendeesToEvent(int estimatedAttendees) {
		event.setEstimatedAttendees(estimatedAttendees);
	}
	
	/**
	 * Use case 1 - Create event
	 * 3.2 in interaction diagram 
	 * Method to set the price of a concert.
	 */
	private void setPriceToConcert(double price) {
		Concert concert = (Concert) event;
		concert.setPrice(price);
	}
	
	/**
	 * Use case 1 - Create event
	 * 3.3 in interaction diagram 
	 * Method to set the description of a event.
	 */
	private void setDescriptionToEvent(String description) {
		event.setDescription(description);
	}
	
	
	/**
	 * Setting the attributes of the rest of Event subclasses.
	 * 
	 */
	private void setDrinkDiscountToBar(double drinkDiscount) {
		Bar bar = (Bar) event;
		bar.setDrinkDiscount(drinkDiscount);
	}
	
	private void setMovieToCinema(String movie) {
		Cinema cinema = (Cinema) event;
		cinema.setMovie(movie);
	}

	private void setMovieGenreToCinema(String movieGenre) {
		Cinema cinema = (Cinema) event;
		cinema.setMovieGenre(movieGenre);
	}
	

	/**
	 * Use case 1 - Create event
	 * 4 in the interaction diagram
	 * 
	 * This method filters event locations based on their capacity and availability
	 * It returns a list of available locations based on capacity, date and time.
	 * 
	 * BigO(N x M). Det er en nested loop, for den første loop filtrerer locations med nok capacity,
	 * og den anden looper igen igennem de fundne locations for at finde ud af om der allerede er en 
	 * anden event registreret på samme dag og samme tid. 
	 * For hver iteration af den ydre løkke kører den indre løkke fuldt ud
	 * 
	 * @throws DataAccessException
	 */
	public List<EventLocation> filterEventLocationOnCapacityAndAvailability() throws DataAccessException {
		//Gets the estimated number of attendees for the event
		int estimatedAttendees = event.getEstimatedAttendees();
		//Lists to store event locations and events
		List<EventLocation> evenLocations = new ArrayList<>();
		List<EventLocation> eventLocationSortedByCapacity = new ArrayList<>();
		List<Event> eventsOnSameDateAndTime = new ArrayList<>();

		//Gets a list of event locations with enough capacity for the estimated attendees
		eventLocationSortedByCapacity = eventLocationCtr.findEventLocation(estimatedAttendees);
		//Checks for existing events on the same date and time as our event
		eventsOnSameDateAndTime = eventDB.checkExistingEventsOnDateAndTime(event.getEventStartTime(),
				event.getEventEndTime());

		//It loops through eventLocationSortedByCapacity which has previously been sorted by capacity.
		//For each location, it initially assumes the location is available.
		for (EventLocation locationsWithCapacityEnough : eventLocationSortedByCapacity) {
			//Assumes the location is available
			boolean isAvailable = true;
			
			//For each loop checks all id's. Returns false if there's a match.
			for (Event eventsHappeningAtTheDateAndTime : eventsOnSameDateAndTime) {
				//Checks if the location is being used by any other event at the same time
				//If the locationsWithCapacityEnough location is being used by 
				//eventsHappeningAtTheDateAndTime, mark it as unavailable and break the loop
				if (eventsHappeningAtTheDateAndTime.getLocationId() == locationsWithCapacityEnough.getLocationId()) {
					isAvailable = false;
					break;
				}
			}
			//If the location ID doesn't match, it's available and get's added to the list
			if (isAvailable) {
				evenLocations.add(locationsWithCapacityEnough);
			}
		}
		//A list of locations are returned. The locations can accommodate the estimated number 
		//of attendees and are available at the events date and time
		return evenLocations;
	}

	/**
	 * Use case 1 - Create Event
	 * 5 in the interaction diagram
	 * 
	 * This method is used to set a location for a specific event.
	 * 
	 * @throws DataAccessException
	 */
	public EventLocation setEventLocation(String locationName) throws DataAccessException {
		//We find the location based on a provided name.
		EventLocation location = eventLocationCtr.findEventLocationByName(locationName);
		//If the location name is found
		if (location != null) {
			//We set the location to the event by giving the eventLocations locationId to the event.
			event.setEventLocation(location);
		}
		//returns the found location (or null if no location was found)
		return location;
	}

	/**
	 * Use case 1 - Create event
	 * 6 in the interaction diagram
	 * 
	 * Responsible for completing events. It does this by calling the
	 * insertCompletedEvent(event) method of the eventDB object. 
	 * The event to be completed and inserted into the database
	 * Event is passed as an argument to the insertCompletedEvent method.
	 * 
	 * The method then returns the updated Event object.
	 * 
	 */
	public Event completedEvent() throws SQLException, DataAccessException {
		//this event is related to the event in eventCtr (variable in the top).
		event = eventDB.insertCompletedEvent(event);
		//returns event that it gets from insertCompletedEvent
		//we want to return our eventID to get it further in the GUI layer.
		return event;
	}

	/**
	 * Get and set event
	 * Get event: Is being used in GUI (confirmationGUI, createConcertGUI, eventLocationGUI).
	 * Set event: CreateAssignmentPart1 - The user chooses and sets the event to our assignment.
	 * We're using it to get events to the GUI layer. Are used to pass Event data 
	 * between different parts of the program.
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}
	
	/**
	 * Not in use anymore. Not a part of the observer pattern.
	 * */
	public int getMaxEventId() throws DataAccessException {
		return eventDB.getMaxEventId();
	}

	/**
	 * Is being used in GUI (mainGUI) to make the list of events.
	 * Is also being used in use case 2 - 1 in the interaction diagram
	 * 
	 * The showAllEvents() method returns a list of all event objects. This
	 * method retrieves the list from eventDB.
	 */
	public List<Event> showAllEvents() throws DataAccessException {
		return eventDB.showAllEvents();
	}

	/**
	 * Is being used in GUI (createConcertGUI) to make the drop down.
	 * 
	 * The findAllBands() method returns a list of all bands. It retrieves the list
	 * from bandDB.
	 */
	public List<Band> findAllBands() throws DataAccessException {
		return bandDB.findAllBands();
	}



	/**
	 * Use case 2
	 * 3 in interaction diagram
	 * GUI layer: CreateAssignmentPart2GUI - Makes an assignment object and adds it to the event object
	 * Returns the assignment.
	 * 
	 * */
	public Assignment addAndCreateAssignment(String assignmentType, String assignmentDescription,
			LocalDateTime assignmentStartTime, LocalDateTime assignmentEndTime, int eventId) {
		//creates an assignment by using the createAssignment() method below
		Assignment createdAssignment = createAssignment(assignmentType, assignmentDescription, assignmentStartTime,
				assignmentEndTime, eventId);
		//adds the created assignment to the current chosen event
		event.addAssignment(createdAssignment);
		
		return createdAssignment;

	}

	/**
	 * Part of the method above. Helping method so the above method doesn't do two things.
	 * Calls the constructor to create the assignment object.
	 * Returns the assignment object to the method addAndCreateAssignment() above.
	 * */
	private Assignment createAssignment(String assignmentType, String assignmentDescription,
			LocalDateTime assignmentStartTime, LocalDateTime assignmentEndTime, int eventId) {
		Assignment assignment = new Assignment(assignmentType, assignmentDescription, assignmentStartTime,
				assignmentEndTime, eventId);
		//returns the created assignment to addAndCreateAssignment
		return assignment;
	}
	
	
	/**
	 * Use case 2
	 * Used in assignmentConfirmationGUI
	 * */
	public boolean confirmAssignments(Event event) throws DataAccessException, SQLException {
		return assignmentCtr.confirmAssignments(event);
		
	}
}

