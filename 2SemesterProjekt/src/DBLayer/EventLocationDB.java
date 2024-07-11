package DBLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CityZipCode;
import model.EventLocation;

/**
 * The EventLocationDB class is an implementation of the EventLocationDBIF
 * interface.
 * 
 * It is responsible for interacting with the database to fetch event location
 * data.
 * 
 * It contains methods to find event locations suitable for an estimated number
 * of attendees and to find an event location by its name.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class EventLocationDB implements EventLocationDBIF {

	/**
	 * These are SQL queries are used to fetch event location data from the
	 * database. A query string is used to add, retrieve or modify data in a
	 * database.
	 * 
	 * selectByNameQ filters the results based on the locationName.
	 */ 
	private static final String selectAllQ = "SELECT EventLocation.*, CityZipCode.cityZipCodeId, CityZipCode.zipCode, CityZipCode.city "
			+ "FROM EventLocation "
			+ "LEFT JOIN CityZipCode ON EventLocation.fk_cityZipCodeId = CityZipCode.cityZipCodeId";
	private static final String selectByNameQ = "SELECT EventLocation.*, CityZipCode.cityZipCodeId, CityZipCode.zipCode, CityZipCode.city "
			+ "FROM EventLocation "
			+ "LEFT JOIN CityZipCode ON EventLocation.fk_cityZipCodeId = CityZipCode.cityZipCodeId "
			+ "WHERE EventLocation.locationName = ?";

	private PreparedStatement findAll;
	private PreparedStatement findByName;

	/**
	 * Constructor for the EventLocationDB class. Two PreparedStatement
	 * findAll and findByName are initialized for executing the SQL queries
	 * selectAllQ and selectByNameQ. This makes us able to call for example
	 * 'findAll' instead of writing a long sentence with getConnection etc. every
	 * time.
	 * 
	 * .prepareStatement(selectAllQ) compiles the SQL query and returns a
	 * PreparedStatement object.
	 * 
	 * @throws DataAccessException
	 */
	public EventLocationDB() throws DataAccessException {
		try {
			// Prepare the SQL queries for execution
			findAll = DBConnection.getInstance().getConnection().prepareStatement(selectAllQ);
			findByName = DBConnection.getInstance().getConnection().prepareStatement(selectByNameQ);

		} catch (SQLException e) {
			// Throw DataAccessException with a specific error message
			throw new DataAccessException(DBMessage.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/**
	 * Use case 1 - Create event
	 * 4.1.1 in interaction diagram. 
	 * 
	 * This method finds and returns a list of EventLocations that can 
	 * accommodate the estimated number of attendees
	 * 
	 * @param estimatedAttendees The estimated number of attendees for the event.
	 * @return A list of event locations that meet the capacity requirement.
	 * @throws DataAccessException If a data access error occurs.
	 */
	@Override
	public List<EventLocation> findEventLocation(int estimatedAttendees) throws DataAccessException {
		List<EventLocation> eventLocationsBuild = new ArrayList<>();
		List<EventLocation> eventLocations = new ArrayList<>();

		ResultSet resultSet = null;

		try {
			//Executes the query to find all EventLocations and store the result
			resultSet = findAll.executeQuery();
			
			//Builds EventLocation objects from the resultset
			eventLocationsBuild = buildObjects(resultSet, estimatedAttendees);
		} catch (SQLException e) {
			//ResultSet could not be read
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);

		}
		//Loops through each EventLocation in the eventLocationsBuild list
		for (EventLocation location : eventLocationsBuild) {
			//If the capacity is greater than or equal to the estimated attendees
			//add it to the eventLocations list
			if (location.getCapacity() >= estimatedAttendees) {
				eventLocations.add(location);
			}
		}
		//Returns the list of locations that can accommodate the estimated attendees
		return eventLocations;
	}

	/**
	 * Use case 1 - Create event
	 * 5.1.1. in interaction diagram 
	 * 
	 * This method finds an EventLocation object by its name.
	 *
	 * @param locationName The name of the event location.
	 * @return An EventLocation object that matches the provided name.
	 * @throws DataAccessException 
	 * @throws SQLException If a database access error occurs.
	 */
	public EventLocation findEventLocationByName(String locationName) throws DataAccessException {
		EventLocation eventLocation = null;
		ResultSet resultSet = null;
		try {
			//Sets the first parameter of the SQL prepared statement to the locationName
			findByName.setString(1, locationName);
			
			//Executes the SQL query and stores the result in the resultset
			resultSet = findByName.executeQuery();
			
			//if there's an result then build a EventLocation object from that result
			if (resultSet.next()) {
				eventLocation = buildObject(resultSet);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		} finally {
			//Finally is used to make sure that the resultset is closed
			if (resultSet != null) {
				try {
					//Closes the resultset
					resultSet.close();
				} catch (SQLException e) {
					throw new DataAccessException("Could not close resultset", e);
				}
			}
		}
		//Returns the EventLocation object. If no matching location is found, it's null
		return eventLocation;
	}

	/**
	 * This method is used to build EventLocation objects from the result of a database query.
	 * 
	 * The buildObject method is used to create a single EventLocation object from
	 * one row of the ResultSet. Is used when you need to process a single row of
	 * data from a ResultSet. The `ResultSet` object contains the results of a SQL
	 * query execution, and each row corresponds to an event location in the
	 * database. This method is used in the findEventLocationByName and the
	 * buildObjects methods.
	 * 
	 * @param resultSet The ResultSet containing the event location data.
	 * @return An EventLocation object.
	 * @throws DataAccessException
	 * @throws SQLException        If a database access error occurs.
	 */
	private EventLocation buildObject(ResultSet resultSet) throws DataAccessException {

		EventLocation result = null;
		try {

			//creates a CityZipCode object from the fields in the result set
			CityZipCode cityZipCode = new CityZipCode(resultSet.getInt("cityZipCodeId"), resultSet.getInt("zipCode"),
					resultSet.getString("city"));
			//creates an eventLocation object from the fields in the resultset
			//It includes the CityZipCode object from earlier
			result = new EventLocation(resultSet.getInt("locationId"), resultSet.getString("locationName"),
					resultSet.getString("locationStreet"), resultSet.getString("locationEmail"),
					resultSet.getString("locationPhoneNo"), resultSet.getInt("capacity"), cityZipCode);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		//Returns the created EventLocation object
		return result;
	}

	/**
	 * The buildObjects method is used when you need to process multiple rows of
	 * data from a ResultSet, creating an EventLocation object for each row and
	 * adding it to a list. Is used when we need to retrieve multiple records from
	 * the database. This method is used in the findEventLocation method.
	 * 
	 * While loop continues as long as there's more rows in the 'resultSet'. For
	 * each row it makes an object. It constructs an EventLocation object from the
	 * data in the current row of the resultSet.
	 * 
	 * The purpose of this method is to construct a list of `EventLocation` objects
	 * from the `ResultSet` obtained from executing a SQL query.
	 * 
	 * @param resultSet          The `ResultSet` containing event location data.
	 * @param estimatedAttendees The estimated number of attendees for the event.
	 * @return A list of `EventLocation` objects that have sufficient capacity for
	 *         the event.
	 * @throws DataAccessException
	 * @throws SQLException        If an error occurs while accessing the data from
	 *                             the ResultSet.
	 */
	private List<EventLocation> buildObjects(ResultSet resultSet, int estimatedAttendees) throws DataAccessException {
		List<EventLocation> result = new ArrayList<>();
		try {
			//For each row in the ResultSet
			while (resultSet.next()) {
				//An EventLocation instance is created
				EventLocation location;
				//The location gets data from the current row of the resultset
				location = buildObject(resultSet);
				//Adds to the list
				result.add(location);
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		//List of eventLocation objects are returned
		return result;
	}

}