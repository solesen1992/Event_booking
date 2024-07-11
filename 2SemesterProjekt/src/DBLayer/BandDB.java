package DBLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Band;

/**
 * 
 * The BandDB class handles database operations related to bands. This includes
 * methods for finding bands by name. It implements the BandDBIF interface.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
public class BandDB implements BandDBIF {

	/**
	 * The SQL query to find bands by name and prepared statements.
	 */
	private static final String findBandsByNamesQ = "SELECT * FROM Band WHERE bandName LIKE ?";
	private static final String findAllBandsQ = "SELECT * FROM Band";
	private PreparedStatement findBandsByNames;
	private PreparedStatement findAllBands;

	/**
	 * Constructor for BandDB class.
	 * 
	 * Initializes the prepared statement. Prepares the SQL statement for finding bands by names
	 * DBConnection.getInstance().getConnection() gets a connection to the database
	 * Throws a DataAccessException if preparing the statement fails
	 * 
	 * @throws DataAccessException if there's an issue preparing the statement
	 */
	public BandDB() throws DataAccessException {

		try {
			// Preparing the SQL statement to find bands by names and find all bands
			findBandsByNames = DBConnection.getInstance().getConnection().prepareStatement(findBandsByNamesQ);
			findAllBands = DBConnection.getInstance().getConnection().prepareStatement(findAllBandsQ);
		} catch (SQLException e) {
			 // Throws a DataAccessException if preparing the statement fails
			throw new DataAccessException(DBMessage.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/**
	 * Retrieves bands from the database that match the provided band name.
	 * 2.4 of the interaction diagram.
	 * This method gets called in addBandsToConcert() in the EventCtr class.
	 * 
	 * 'findBandsByNames' is our prepared statement found in the start of the class.
	 * 
	 * @param bandName The name of the band to search for
	 * @return A list of Band objects matching the provided name
	 * @throws DataAccessException if there's an issue retrieving bands from the database
	 */
	public List<Band> findBandsByNames(String bandName) throws DataAccessException {
		ResultSet resultSet;
		try {
			//Replaces the first placeholder (1) with the value of bandName
			findBandsByNames.setString(1, bandName);
			//executes the query to retrieve bands matching the provided name
			resultSet = findBandsByNames.executeQuery();
			// Builds the list of Band objects from the retrieved resultSet
			// buildObjects() creates a list of objects.
			List<Band> findBandsByNamesResult = buildObjects(resultSet);
			// Returns the list of Band objects
			return findBandsByNamesResult;
		} catch (SQLException e) {
			// Throw a DataAccessException in case of a SQL exception
			throw new DataAccessException("Could not retrieve all bands", e);
		}
	}

	/**
	 * This method is used to retrieve a list of all bands from the database.
	 * Is called in findAllBands() in the EventCtr class through BandDBIF.
	 * The method in EventCtr is used in the GUI layer (createConcertGUI) to make the drop down.
	 * 
	 * @return List of all bands found in the database.
	 * @throws DataAccessException If retrieving bands fails due to a SQL exception.
	 */
	public List<Band> findAllBands() throws DataAccessException {
		//A list to store the bands
		List<Band> allBandsFound  = null;
		try {
			//ResultSet object to store the results of the database query
			ResultSet resultSet;

			//Executes the SQL query to find all bands and store the results in the resultSet
			resultSet = findAllBands.executeQuery();
			//Builds Band objects from the resultSet
			allBandsFound = buildObjects(resultSet);
		} catch (SQLException e) {
			//If an SQL error occurs while reading the resultSet
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		//returns the list of bands
		return allBandsFound;
	}

	/**
	 * Builds a Band object from the provided ResultSet.
	 * It extracts the band data from the ResultSet and creates a Band object.
	 * 
	 * Gets called in buildObjects() in BandDB
	 * 
	 * @param resultSet The ResultSet containing band data
	 * @return A Band object built from the ResultSet
	 * @throws DataAccessException 
	 * @throws SQLException if there's an issue accessing data in the ResultSet
	 */
	private Band buildObject(ResultSet resultSet) throws DataAccessException  {
		//A Band object
		Band band;
		try {
			//creates a new Band object
			//We get the bands genre etc from the resultSet
			band = new Band(resultSet.getString("bandGenre"), resultSet.getString("bandDescription"),
					resultSet.getString("bandName"));
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		//we return our band object
		return band;
	}

	/**
	 * Builds a list of Band objects from the provided ResultSet and returns it.
	 * We build an empty list to store Band objects.
	 * The while loop iterates through the resultSet and builds Band objects.
	 * 
	 * Gets called in findBandsByName() and findAllBands() in bandDB.
	 * 
	 * @param resultSet The ResultSet containing band data
	 * @return A list of Band objects built from the ResultSet
	 * @throws DataAccessException 
	 * @throws SQLException if there's an issue accessing data in the ResultSet
	 */
	private List<Band> buildObjects(ResultSet resultSet) throws DataAccessException  {
		List<Band> findBandsByNamesResult = new ArrayList<>();
		
		try {
			//We iterate through each row in the result set
			while (resultSet.next()) {
				//For each row, build a band object and add it to the list
				findBandsByNamesResult.add(buildObject(resultSet));
			}
			//If there's an error reading from the result set, throw a DataAccessException
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
		}
		//Return the list of Band objects
		return findBandsByNamesResult;

	}

}
