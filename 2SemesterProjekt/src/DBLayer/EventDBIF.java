package DBLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.Event;

/**
 * 
 * The EventDBIF interface establishes a contract for all classes that implement it.
 * 
 * The EventDBIF interface ensures that any class implementing it must provide implementations 
 * for the checkExistingEventsOnDateAndTime, insertCompletedEvent, and showAllEvents methods.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public interface EventDBIF {
	List<Event> checkExistingEventsOnDateAndTime(LocalDateTime eventStartTime, LocalDateTime eventEndTime) throws  DataAccessException;
	Event insertCompletedEvent(Event event)throws SQLException, DataAccessException;
	List<Event> showAllEvents() throws  DataAccessException;
	public int getMaxEventId() throws DataAccessException;
}
 
