package controllerLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBLayer.AssignmentDB;
import DBLayer.AssignmentDBIF;
import DBLayer.DataAccessException;
import model.Assignment;
import model.Event;


/**
 * AssignmentCtr is a controller class for managing 'Assignment' instances in the application.
 * 
 * It interacts with the database through the 'AssignmentDB' interface, which is implemented 
 * by 'AssignmentDBIF'
 * 
 * The class provides two methods:
 * 'confirmAssignments(Event event)': This method is used to confirm and store all assignments 
 * related to a specific event. It adds the assignments to the database.
 * 
 * 'findAllAssignments()': This method is used in the GUI layer to find all assignments in 
 * the database and add them to the assignment overview.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class AssignmentCtr {
	private AssignmentDBIF assignmentDB;
	private List<Assignment> confirmedAssignments;
	 
	/**
	 * Constructor
	 * */
	public AssignmentCtr() throws DataAccessException {
		this.assignmentDB = new AssignmentDB();
		confirmedAssignments = new ArrayList<Assignment>();
	}

    
    /**
     * Use case 2 - Create assignment
     * 4.1 in the interaction diagram
     * 
     * This method is used to confirm and store all assignments related to a specific event.
     * It adds it to the database.
     * 
     * @throws SQLException 
     * */
	public boolean confirmAssignments(Event event) throws DataAccessException, SQLException  {
		boolean res = false;
       
        	//insertAllAssignments are responsible for inserting all assignments 
        	//associated with a specific event into the database
            assignmentDB.insertAllAssignments(event);
           
           // If everything goes well, return true
           res = true;
       
        	 //Adds all assignments from the current event to the confirmedAssignments list.
            //getAssignments gets a list of current assignments from your current event object        
        confirmedAssignments.addAll(event.getAssignments());
        return res;
    }
	
	/**
	 * Use case 2
	 * Used in the GUI layer.
	 * 
	 * Method to find all assignments in the database and add it to 
	 * the assignment overview: "vagt oversigt".
	 * */
	public List<Assignment> findAllAssignments() throws DataAccessException{
		return assignmentDB.findAllAssignments();
	}
}
