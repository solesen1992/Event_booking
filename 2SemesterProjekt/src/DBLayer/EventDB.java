package DBLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Bar;
import model.Cinema;
import model.CommunityKitchen;
import model.Concert;
import model.Event;

/**
 * The class EventDB is used for managing and interacting with Event data in a
 * database. Such as retrieving all events or inserting a new event.
 * 
 * The class provides methods for querying all events (showAllEvents()),
 * inserting a completed event (insertCompletedEvent()), and checking existing
 * events on a specific date and time (checkExistingEventsOnDateAndTime()).
 * 
 * It also includes methods for building Event objects from ResultSet data
 * returned from the database. Different types of events (Concert, Bar, Cinema,
 * CommunityKitchen) are handled using a switch case in the buildObject()
 * method.
 * 
 * EventDB implements the EventDBIF interface
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
 
public class EventDB implements EventDBIF {
	/**
	 * `selectAllQ` is a query that selects all records from the "event" table.
	 * `insertQueryQ` is an SQL insert command that adds a new event to the "event"
	 * table, specifying the columns to be filled and using placeholders (?), which
	 * will be replaced with actual values when the command is executed.
	 * 
	 * `PreparedStatement` objects `selectAll` and `insertQuery` are used to execute
	 * the SQL queries.
	 */
	private static final String selectAllQ = "select * from event";
	private static final String insertQueryQ = "INSERT INTO Event (eventType, eventTitle, eventStartTime, eventEndTime, eventDescription, estimatedAttendees, fk_locationId) \r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	// MAX() returns the largest value of the selected column. The result is called
	// 'maxEventId'.
	private static final String maxEventIdQuery = "SELECT MAX(eventId) AS maxEventId FROM Event";
	private PreparedStatement selectAll;
	private PreparedStatement insertQuery;
	private PreparedStatement selectMax;

	private List<Event> events;

	/**
	 * The constructor prepares the SQL statements and throws exceptions if there
	 * are SQL or data access errors. It uses the singleton `DBConnection` class to
	 * get a database connection and prepare the statements.
	 */
	public EventDB() throws DataAccessException {
		try {
			selectAll = DBConnection.getInstance().getConnection().prepareStatement(selectAllQ);
			selectMax = DBConnection.getInstance().getConnection().prepareStatement(maxEventIdQuery);
			//prepared statement method gets the string query and gets into the database
			//to retrieve information.
			//return_generated_keys to get the eventID back and saves it in the resultset
			insertQuery = DBConnection.getInstance().getConnection().prepareStatement(insertQueryQ,
					PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/*
	 * Use case 1 - Create event 1.3 in the interaction diagram
	 * 
	 * Method checks if there are existing events that conflict with a given date
	 * and time
	 * 
	 * This method retrieves all events that overlap with a given time period. It
	 * uses the selectAll query to get all events from the database, builds an Event
	 * object for each one, and adds it to a list if it overlaps with the given time
	 * period.
	 * 
	 * @return existingEvents
	 */
	public List<Event> checkExistingEventsOnDateAndTime(LocalDateTime eventStartTime, LocalDateTime eventEndTime)
			throws DataAccessException {
		List<Event> existingEvents = new ArrayList<>();
		ResultSet resultSet = null;

		try {
			// Execute a query to select all events.
			resultSet = selectAll.executeQuery();
			// Builds event objects from the results of the query.
			events = buildObjects(resultSet, eventStartTime, eventEndTime);

			/**
			 * For each event, if the event's start time is before or at the same time as
			 * the end time and the event's end time is after or at the same time as the
			 * start time, add the event to the list of existing events.
			 */
			for (Event event : events) {
				if ((event.getEventStartTime().isBefore(eventEndTime)
						|| event.getEventStartTime().isEqual(eventEndTime))
						&& (event.getEventEndTime().isAfter(eventStartTime)
								|| event.getEventEndTime().isEqual(eventStartTime))) {
					existingEvents.add(event);
				}

			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		// returns the list of existing events that conflicts with the time and date
		return existingEvents;
	}

	/**
	 * This method builds a list of Event objects from a ResultSet. It only adds an
	 * event to the list if it overlaps with the given time period.
	 */
	private List<Event> buildObjects(ResultSet resultSet, LocalDateTime eventStartTime, LocalDateTime eventEndTime)
			throws DataAccessException {
		// Creating a new list to hold the Event objects
		List<Event> result = new ArrayList<>();
		try {

			// Using a while loop to iterate through each row in the ResultSet
			while (resultSet.next()) {
				// For each row, an Event object is built using the buildObject method
				Event event = buildObject(resultSet);

				/**
				 * Check if the event's start time is before or equal to the given end time. And
				 * if the event's end time is after or equal to the given start time If these
				 * conditions are met, the event is added to the list
				 */
				if ((event.getEventStartTime().isBefore(eventEndTime)
						|| event.getEventStartTime().isEqual(eventEndTime))
						&& (event.getEventEndTime().isAfter(eventStartTime)
								|| event.getEventEndTime().isEqual(eventStartTime))) {
					// if both conditions are met, it adds the event
					result.add(event);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		// returns the list
		return result;
	}

	/**
	 * This method builds an Event object from a ResultSet row. It uses the event
	 * type to determine which subclass of Event to instantiate (Concert, Bar,
	 * Cinema, or CommunityKitchen). If the event type is not recognized, it throws
	 * an exception.
	 */
	private Event buildObject(ResultSet resultSet) throws DataAccessException {

		Event result = null;
		try {
			// Extract the eventType from the ResultSet
			String eventType = resultSet.getString("eventType");

			// Switch statement to handle different event types
			switch (eventType) {
			// Case for Concert event type
			case "Koncert":
				// Creates a new Concert object with the necessary parameters
				result = new Concert(resultSet.getString("eventType"), resultSet.getString("eventTitle"),
						//expects the query to be the type Timestamp
						//converts Timestamp to LocalDateTime
						resultSet.getTimestamp("eventStartTime").toLocalDateTime(),
						resultSet.getTimestamp("eventEndTime").toLocalDateTime(), null, resultSet.getInt("eventId"));
				// Gets the locationId from the resultSet. Sets the locationId from that.
				//foreign key that refers to a location in another table.
				result.setLocationId(resultSet.getInt("fk_locationId"));
				break;

			// Case for Bar event type
			case "Bar":
				// Creates a new Bar object with the necessary parameters
				result = new Bar(resultSet.getString("eventType"), resultSet.getString("eventTitle"),
						resultSet.getTimestamp("eventStartTime").toLocalDateTime(),
						resultSet.getTimestamp("eventEndTime").toLocalDateTime(), null, resultSet.getInt("eventId"));
				// Gets the locationId from the resultSet. Sets the locationId from that.
				result.setLocationId(resultSet.getInt("fk_locationId"));
				break;

			// Case for Cinema event type
			case "Biograf":
				// Creates a new Cinema object with the necessary parameters
				result = new Cinema(resultSet.getString("eventType"), resultSet.getString("eventTitle"),
						resultSet.getTimestamp("eventStartTime").toLocalDateTime(),
						resultSet.getTimestamp("eventEndTime").toLocalDateTime(), null, resultSet.getInt("eventId"));
				// Gets the locationId from the resultSet. Sets the locationId from that.
				result.setLocationId(resultSet.getInt("fk_locationId"));
				break;

			// Case for CommunityKitchen event type
			case "Folkekøkken":
				// Creates a new CommunityKitchen object with the necessary parameters
				result = new CommunityKitchen(resultSet.getString("eventType"), resultSet.getString("eventTitle"),
						resultSet.getTimestamp("eventStartTime").toLocalDateTime(),
						resultSet.getTimestamp("eventEndTime").toLocalDateTime(), null, resultSet.getInt("eventId"));
				// Gets the locationId from the resultSet. Sets the locationId from that.
				result.setLocationId(resultSet.getInt("fk_locationId"));

			default:
				// If no event was created, an exception is thrown
				if (result == null) {
					throw new SQLException("Unrecognized event type: " + eventType);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		// Returns the created event object
		return result;
	}

	/**
	 * Use case 1 - Create Event 6.1 in the interaction diagram ± This method
	 * inserts a completed event into the database. It begins a transaction, sets
	 * the parameters of the insertQuery using the properties of the Event object,
	 * executes the query, and commits the transaction. If any exception occurs, it
	 * rolls back the transaction.
	 * 
	 * @throws SQLException
	 */
	public synchronized Event insertCompletedEvent(Event event) throws DataAccessException, SQLException {
		// Starts a transaction by getting an instance of the database connection
		DBConnection.getInstance().startTransaction();

		// Set values for each parameter in the INSERT SQL query
		try {
			insertQuery.setString(1, event.getEventType());
			insertQuery.setString(2, event.getEventTitle());
			insertQuery.setObject(3, event.getEventStartTime());
			insertQuery.setObject(4, event.getEventEndTime());
			insertQuery.setString(5, event.getDescription());
			insertQuery.setInt(6, event.getEstimatedAttendees());
			insertQuery.setInt(7, event.getEventLocation().getLocationId());

			// Execute the SQL query and get the number of generated keys
			// Returns the generated EventID from the event we just created
			int generatedKeys = DBConnection.getInstance().executeInsertWithIdentity(insertQuery);

			// If no generated key were affected by the query, throw an exception
			if (generatedKeys == 0) {
				throw new SQLException("Inserting event failed, no key affected.");
			}
			// If the query was successful, commit the transaction
			DBConnection.getInstance().commitTransaction();

			// set eventId to generated key
			event.setEventId(generatedKeys);
			// returns event object that was inserted into the database
			return event;

		} catch (SQLException e) {
			// If an exception occurred, rollback the transaction
			DBConnection.getInstance().rollbackTransaction();
			// Re-throw the exception
			throw e;
		//Runs no matter what
		} finally {
			// Close the PreparedStatement in the finally block
			// It's null if it isn't initialized correctly in the constructor
			// if an exception occurs
			if (insertQuery != null) {
				insertQuery.close();
			}
		}
	}

	/**
	 * Used in the GUI layer Use case 1 - Events - overview of events Use case 2 -
	 * AssignmentMainGUI - Used to get the eventID from events. Shows the eventID in
	 * the assignment overview. Use case 2 - CreateAssignmentPart1GUI where you have
	 * an overview of events before you make an assignment
	 * 
	 * 
	 * This method retrieves all events from the database. It executes the selectAll
	 * query, builds an Event object for each row in the ResultSet, and adds it to a
	 * list.
	 * 
	 * @throws DataAccessException
	 */
	public List<Event> showAllEvents() throws DataAccessException {
		List<Event> events = new ArrayList<>();
		ResultSet resultSet = null; // will be used to hold the results of a database query

		try {
			// The executeQuery method is called on the selectAll object to retrieve all
			// records
			// from the database
			resultSet = selectAll.executeQuery();

			// Iterates over each row in the resultSet
			while (resultSet.next()) {
				// For each row in the resultSet, an Event object is built using
				// the buildObject method
				Event event = buildObject(resultSet);
				// adds the Event object to the list
				events.add(event);
			}

		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}

		// returns a list of all events retrieved from the database
		return events;
	}

	/**
	 * Part of Observer pattern Gets the maxID from the database.
	 * 
	 * The initial value of int maxEventId = -1 is done to handle situation where
	 * there might be no records in the database - or the SQL query fails to
	 * execute. If the maxEventId remains -1 after executing the query, it indicates
	 * there were no valid event ID's retrieved.
	 * 
	 * -1 = no found results.
	 * 
	 * selectMax: The SQL code 'SELECT MAX(eventId) AS maxEventId FROM Event' is
	 * used to retrieve the maximum value of the column 'eventId' from the 'Event'
	 * table in a database. The result is aliased as 'maxEventId'. That means that
	 * the returned column in the result set will be named 'maxEventId'.
	 * 
	 */
	public synchronized int getMaxEventId() throws DataAccessException {
		// initialize the maximum event ID as -1
		int maxEventId = -1;
		ResultSet resultSet = null;
		try {
			// executes the selectMax SQL query and store the result in resultSet
			resultSet = selectMax.executeQuery();
			// if the resultSet is not empty
			// next() controls if there's rows in the resultset
			if (resultSet.next()) {
				// takes the value of the column named "maxEventId" from the resultSet
				maxEventId = resultSet.getInt("maxEventId");
			}
			// if a SQL exception is encountered
		} catch (SQLException e) {
			// throw a new DataAccessException with a message
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		// returns the maximum event ID
		return maxEventId;
	}

}
