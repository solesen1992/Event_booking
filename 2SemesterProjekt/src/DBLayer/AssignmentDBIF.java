package DBLayer;

import java.sql.SQLException;
import java.util.List;

import model.Assignment;
import model.Event;

/**
 * 
 * The AssignmentDBIF interface establishes a contract for all classes that implement it.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public interface AssignmentDBIF {

	void insertAllAssignments(Event event) throws DataAccessException, SQLException;
	public List<Assignment> findAllAssignments() throws DataAccessException;
}
 