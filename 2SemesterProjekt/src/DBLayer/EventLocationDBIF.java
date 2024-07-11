package DBLayer;

import java.util.List;

import model.EventLocation;

/**
 * 
 * The EventLocationDBIF interface establishes a contract for all classes that implement it.
 * 
 * This contract ensures that all implementing classes will have methods to find an event location
 * based on the estimated number of attendees and to find an event location by its name.
 * 
 * By defining these methods in an interface, we allow for different implementations that
 * can be easily switched out as needed.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
 
public interface EventLocationDBIF {
	public List<EventLocation> findEventLocation(int estimatedAttendees) throws DataAccessException;
	public EventLocation findEventLocationByName(String locationName) throws  DataAccessException;
}
