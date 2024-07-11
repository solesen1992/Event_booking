package DBLayer;

/**
 * `DBMessage` defines two static final String variables:
 * `COULD_NOT_PREPARE_STATEMENT` and `COULD_NOT_BIND_OR_EXECUTE_QUERY`.
 * 
 * These variables hold specific error messages related to database operations.
 * 
 * `COULD_NOT_PREPARE_STATEMENT` is used when there's a problem preparing a SQL
 * statement (for example, when using `PreparedStatement`).
 * 
 * `COULD_NOT_BIND_OR_EXECUTE_QUERY` is used when there's an issue with setting
 * parameters for a SQL statement or executing it.
 * 
 * The class was created is to centralize and standardize error messages. It
 * helps with code maintainability and readability. If you need to change the
 * error message, you only have to change it in one place. Moreover, it can
 * avoid potential typos and inconsistencies in error messages throughout the
 * codebase.
 * 
 * @author Gruppe 3 , knol
 * @version 2018-08-30
 */
 
public class DBMessage {
	//Used when there is an error reading from the result set.
	public static final String COULD_NOT_READ_RESULTSET = "Could not read resultset";
	
	//Used when there is an error preparing the SQL statement
	public static final String COULD_NOT_PREPARE_STATEMENT = "Could not prepare statement";
	
	//Used when there is an error binding or executing the SQL query.
	public static final String COULD_NOT_BIND_OR_EXECUTE_QUERY = "Could not bind or execute query";
	
	//Used when there is an error binding the PreparedStatement variables for an insert operation.
	public static final String COULD_NOT_BIND_PS_VARS_INSERT = "Could not bind ps variables for insert";
	
	//Used when there is an error inserting a new record in the database
	public static final String COULD_NOT_INSERT = "Could not insert new record";
	
	//Used when there is an error rolling back a transaction.
	public static final String COULD_NOT_ROLLBACK = "Could not roll back";
	
	//Used when there is an error inserting all assignments into the database.
	public static final String COULD_NOT_INSERT_ALL_ASSIGNMENTS = "Could not insert all assignment";
	
	//Used when there is an error restoring the auto-commit setting of the database connection.
	public static final String COULD_NOT_RESTORE_AUTOCOMMIT = "Could not restore autocommit";
	
	//Used when there is an error connecting to the database.
	public static final String COULD_NOT_CONNECT_TO_DATABASE = "Could not connect to database";
}
