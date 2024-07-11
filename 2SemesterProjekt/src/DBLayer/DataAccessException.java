package DBLayer;

/**
 * 
 * DataAccessException class
 * 
 * The DataAccessException class is a custom exception class that is thrown when there is an issue 
 * accessing data from the database. The class extends the built-in Exception class in Java, 
 * allowing it to be caught and handled like other exceptions. This class is useful for handling 
 * errors and issues that arise when interacting with the database and can provide more context 
 * and detail about what went wrong.
 * 
 * @author knol
 * @version 2018-08-30
 */

public class DataAccessException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * */
	public DataAccessException(String message, Throwable e) {
		super(message, e);
	}
}
 