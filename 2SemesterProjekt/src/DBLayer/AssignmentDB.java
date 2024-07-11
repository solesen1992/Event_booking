/**
 * 
 */
package DBLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Assignment;
import model.Event;

/**
 * 'AssignmentDB' is responsible for executing SQL queries related to the Assignment objects 
 * in the database. It implements the 'AssignmentDBIF' interface.
 * 
 * The class includes methods to prepare and execute SQL queries for the 'Assignment' table 
 * in the database. This includes methods for selecting all assignments 
 * and inserting new assignments.
 * 
 * 'sertAllAssignments(Event event)' is used to insert all assignment objects associated 
 * with the current event object into the database. This method uses a database 
 * transaction to ensure that all operations either fail or succeed as a single unit. 
 * If the 'PreparedStatement' is closed, a new one is prepared to prevent exceptions.
 * 
 * 'findAllAssignments()` retrieves all assignments from the database.
 * 
 * 'buildObject(ResultSet resultSet)' builds an Assignment object from a ResultSet of a SQL query.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
 
public class AssignmentDB implements AssignmentDBIF {
	/**
	 * SQL queries and prepared statements
	 */
	private static final String selectAllQ = "SELECT * from Assignment";
	private static final String insertQueryQ = "INSERT INTO Assignment(AssignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, fk_eventId) \r\n"
			+ "VALUES (?, ?, ?, ?, ?)";
	private PreparedStatement selectAll;
	private PreparedStatement insertQuery;

	/**
	 * Constructor
	 * 
	 * selectAll = Prepares a SQL statement for selecting all records from the
	 * database table. DBConnection.getInstance().getConnection() gets a connection
	 * to the database prepareStatement(selectAllQ) prepares a SQL statement -
	 * specifically selectAllQ for execution
	 */
	public AssignmentDB() throws DataAccessException {
		// a try block to catch the SQL exceptions
		try {
			selectAll = DBConnection.getInstance().getConnection().prepareStatement(selectAllQ);
			insertQuery = DBConnection.getInstance().getConnection().prepareStatement(insertQueryQ);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessage.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	/**
	 * This method builds an Assignment object from a resultSet.
	 */
    private Assignment buildObject(ResultSet resultSet) throws SQLException {
    	//Makes an assignment object with null values
        Assignment assignment = new Assignment(null, null, null, null, 0);
        //Sets the parameters based on the resultset
        //Sets the assignment type from the resultset etc.
        assignment.setAssignmentType(resultSet.getString("AssignmentType"));
        assignment.setAssignmentDescription(resultSet.getString("assignmentDescription"));
        assignment.setAssignmentStartTime(resultSet.getTimestamp("assignmentStartTime").toLocalDateTime());
        assignment.setAssignmentEndTime(resultSet.getTimestamp("assignmentEndTime").toLocalDateTime());
        assignment.setEventId(resultSet.getInt("fk_eventId"));
        //Returns the built assignment object
        return assignment;
    }
    
    

	/**
	 * Use case 2 - 4.1.1. in the interaction diagram
	 * 
	 * This method is used to insert all our current assignment objects associated with 
	 * our current event object into the database.
	 * 
	 * A transaction ensures that all database operations fail or succeed as a single unit.
	 * We check if the prepared statement is closed to ensure that it's available for use.
	 * If it's closed, we need to prepare a new one. It's to prevent exceptions when trying
	 * to use a closed prepared statement.
	 * 
	 * @throws SQLException
	 */

	public void insertAllAssignments(Event event) throws DataAccessException, SQLException {
	    // Starts the database transaction.
		// getInstance() is a singleton
	    DBConnection.getInstance().startTransaction();
	    try {
	    	//If the insertQuery preparedStatement is closed.
	        if(insertQuery.isClosed()) {
	        	//Prepare a new statement if it's closed where it tries to get connection
	        	insertQuery = DBConnection.getInstance().getConnection().prepareStatement(insertQueryQ);
	        }
	        // Loops through all assignments in the event
	        for (Assignment assignment : event.getAssignments()) {
	            // Sets the parameters in the PreparedStatement with values from the assignment
	            insertQuery.setString(1, assignment.getAssignmentType());
	            insertQuery.setString(2, assignment.getAssignmentDescription());
	            insertQuery.setObject(3, assignment.getAssignmentStartTime());
	            insertQuery.setObject(4, assignment.getAssignmentEndTime());
	            insertQuery.setInt(5, assignment.getEventId());
	            
	            // Executes the PreparedStatement and stores the number of affected rows
	            int rowsAffected = insertQuery.executeUpdate();
	            
	            // If no rows were affected, throw an exception
	            if (rowsAffected == 0) {
	                throw new SQLException("Inserting assignment failed, no rows affected.");
	            }
	        }
	        
	        // Commits the database transaction if everything succeeds
	        DBConnection.getInstance().commitTransaction();
	    } catch (SQLException e) {
	    	// Rollbacks the transaction in case an exception occurs
	        DBConnection.getInstance().rollbackTransaction();
	        //Re-throws the caught exception
	        throw e;
	    } finally {
	    	// Closes the PreparedStatement, regardless of whether the try block succeeded 
	    	//or an exception occurred
	        if (insertQuery != null) {
	            insertQuery.close();
	        }
	    }
	}
	
	
	  /**
	   * Used in the GUI layer
	   * Used to show all assignments in the assignment overview: 'Vagt oversigt'.
	   * 
	   * This method retrieves all assignments from the database. It executes the
	   * selectAll query, builds an Assignment object for each row in the ResultSet,
	   * and adds it to a list.
	   * 
	   * @throws DataAccessException
	   * */
    public List<Assignment> findAllAssignments() throws DataAccessException {
        List<Assignment> assignments = new ArrayList<>();
        ResultSet resultSet = null;
        try {
        	// Executes the SQL query that selects all assignments
            resultSet = selectAll.executeQuery();
            // Loops through the result set
            while (resultSet.next()) {
            	// Builds an Assignment object for each row in the result set
                Assignment assignment = buildObject(resultSet);
                // Adds the Assignment object to the list
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            throw new DataAccessException(DBMessage.COULD_NOT_READ_RESULTSET, e);
        }
        // Return the list of assignments
		return assignments; 
    }
}