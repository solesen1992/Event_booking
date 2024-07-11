package controllerLayer;

import java.util.List;

import DBLayer.DataAccessException;
import DBLayer.EventLocationDB;
import DBLayer.EventLocationDBIF;
import model.EventLocation;

/**
 * The EventLocationCtr class is responsible for managing event locations within the system.
 * It interacts with the database to fetch event locations based on certain criteria.
 * It consists of methods to find event locations suitable for an estimated number of attendees
 * and to find an event location by its name.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class EventLocationCtr {
	private EventLocationDBIF eventLocationDB;

	/**
	 * Creating a new object. 
	 * */
	public EventLocationCtr() throws DataAccessException {
		eventLocationDB = new EventLocationDB();
	}
 
	/**
	 * Use case 1 - Create event
	 * 4.1 in the interaction diagram.
	 * 
	 * This methods finds event locations that can accommodate a specified number of attendees.
	 * An object of EventLocationDB is called to retrieve a list of event locations
	 * that can accommodate the estimated number of attendees.
	 * */
	public List<EventLocation> findEventLocation(int estimatedAttendees) throws DataAccessException {
		return eventLocationDB.findEventLocation(estimatedAttendees);
	}
	
	/**
	 * Use case 1 - Create event
	 * 5.1 in the interaction diagram
	 * 
	 * This method is used to find an EventLocation object by its name
	 * 
	 * findEventLocationByName interacts with the database to find an EventLocation object
	 * that matches the name specified by LocationName.
	 * If such name is found, it's returned by the method and assigned to the location variable.
	 * 
	 * In the end it returns the 'location'.
	 * @throws DataAccessException 
	 * */
	public EventLocation findEventLocationByName(String LocationName) throws DataAccessException {
		EventLocation location = null;
		//finds a eventLocation object by its name
		location = eventLocationDB.findEventLocationByName(LocationName);
		//returns the found EventLocation object
		return location;
	}

}
